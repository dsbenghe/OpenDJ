package org.opends.schema;

import org.opends.server.util.Validator;
import org.opends.server.types.ByteSequence;
import org.opends.ldap.DecodeException;
import org.opends.util.SubstringReader;
import org.opends.messages.Message;
import static org.opends.messages.SchemaMessages.*;
import org.opends.types.ConditionResult;

import java.util.*;

/**
 * This class defines a data structure for storing and interacting
 * with matching rules, which are used by servers to compare 
 * attribute values against assertion values when performing Search
 * and Compare operations.  They are also used to identify the value
 * to be added or deleted when modifying entries, and are used when
 * comparing a purported distinguished name with the name of an entry.
 * <p>
 * Matching rule implementations must extend the
 * <code>MatchingRuleImplementation</code> class so they can be used by
 * OpenDS.
 * <p>
 * Where ordered sets of names, or extra properties are provided, the
 * ordering will be preserved when the associated fields are accessed
 * via their getters or via the {@link #toString()} methods.
 */
public abstract class MatchingRule extends AbstractSchemaElement
{
  // The OID that may be used to reference this definition.
  protected final String oid;

  // The set of user defined names for this definition.
  protected final SortedSet<String> names;

  // Indicates whether this definition is declared "obsolete".
  protected final boolean isObsolete;

  protected final String syntaxOID;

  // The definition string used to create this objectclass.
  protected final String definition;

  protected MatchingRule(String oid,
                     SortedSet<String> names,
                     String description,
                     boolean obsolete,
                     String syntax,
                     Map<String, List<String>> extraProperties,
                     String definition)
  {
    super(description, extraProperties);

    this.oid = oid;
    this.names = names;
    this.isObsolete = obsolete;
    this.syntaxOID = syntax;

    if(definition != null)
    {
      this.definition = definition;
    }
    else
    {
      this.definition = buildDefinition();
    }
  }

      /**
   * Retrieves the OID for this schema definition.
   *
   * @return The OID for this schema definition.
   */
  public final String getOID() {

    return oid;
  }

  /**
   * Retrieves an iterable over the set of user-defined names that may
   * be used to reference this schema definition.
   *
   * @return Returns an iterable over the set of user-defined names
   *         that may be used to reference this schema definition.
   */
  public Iterable<String> getNames() {
    return names;
  }

  /**
   * Indicates whether this schema definition has the specified name.
   *
   * @param name
   *          The name for which to make the determination.
   * @return <code>true</code> if the specified name is assigned to
   *         this schema definition, or <code>false</code> if not.
   */
  public boolean hasName(String name) {
    for(String n : names)
    {
      if(n.equalsIgnoreCase(name))
      {
        return true;
      }
    }
    return false;
  }


  /**
   * Retrieves the name or OID for this schema definition. If it has
   * one or more names, then the primary name will be returned. If it
   * does not have any names, then the OID will be returned.
   *
   * @return The name or OID for this schema definition.
   */
  public String getNameOrOID() {
    if(names.isEmpty())
    {
      return oid;
    }
    return names.first();
  }

  /**
   * Indicates whether this schema definition has the specified name
   * or OID.
   *
   * @param value
   *          The value for which to make the determination.
   * @return <code>true</code> if the provided value matches the OID
   *         or one of the names assigned to this schema definition,
   *         or <code>false</code> if not.
   */
  public boolean hasNameOrOID(String value) {
    return hasName(value) ||
        getOID().equals(value);
  }



  /**
   * Indicates whether this schema definition is declared "obsolete".
   *
   * @return <code>true</code> if this schema definition is declared
   *         "obsolete", or <code>false</code> if not.
   */
  public final boolean isObsolete()
  {
    return isObsolete;
  }

  /**
   * Retrieves the OID of the assertion value syntax with which this matching
   * rule is associated.
   *
   * @return The OID of the assertion value syntax with which this matching
   *         rule is associated.
   */
  public abstract Syntax getSyntax();

  /**
   * Indicates whether the provided attribute value should be
   * considered a match for the given assertion value. The assertion value is
   * guarenteed to be valid against this matching rule's assertion syntax.
   *
   * @param attributeValue The attribute value.
   * @param assertionValue The schema checked assertion value.
   * @return {@code TRUE} if the attribute value should be considered
   *         a match for the provided assertion value, {@code FALSE}
   *         if it does not match, or {@code UNDEFINED} if the result
   *         is undefined.
   */
  public abstract ConditionResult valuesMatch(ByteSequence attributeValue,
                                              ByteSequence assertionValue);

  protected final void toStringContent(StringBuilder buffer)
  {
    buffer.append(oid);

    if (!names.isEmpty()) {
      Iterator<String> iterator = names.iterator();

      String firstName = iterator.next();
      if (iterator.hasNext()) {
        buffer.append(" NAME ( '");
        buffer.append(firstName);

        while (iterator.hasNext()) {
          buffer.append("' '");
          buffer.append(iterator.next());
        }

        buffer.append("' )");
      } else {
        buffer.append(" NAME '");
        buffer.append(firstName);
        buffer.append("'");
      }
    }

    if ((description != null) && (description.length() > 0)) {
      buffer.append(" DESC '");
      buffer.append(description);
      buffer.append("'");
    }

    if (isObsolete) {
      buffer.append(" OBSOLETE");
    }

    buffer.append(" SYNTAX ");
    buffer.append(syntaxOID);
  }

  /**
   * Retrieves the string representation of this schema definition in
   * the form specified in RFC 2252.
   *
   * @return The string representation of this schema definition in
   *         the form specified in RFC 2252.
   */
  public final String toString() {
    return definition;
  }

  @Override
  public final int hashCode() {
    return oid.hashCode();
  }

  public static MatchingRule decode(String definition)
      throws DecodeException
  {
    SubstringReader reader = new SubstringReader(definition);

    // We'll do this a character at a time.  First, skip over any leading
    // whitespace.
    reader.skipWhitespaces();

    if (reader.remaining() <= 0)
    {
      // This means that the value was empty or contained only whitespace.  That
      // is illegal.
      Message message = ERR_ATTR_SYNTAX_MR_EMPTY_VALUE.get();
      throw new DecodeException(message);
    }


    // The next character must be an open parenthesis.  If it is not, then that
    // is an error.
    char c = reader.read();
    if (c != '(')
    {
      Message message = ERR_ATTR_SYNTAX_MR_EXPECTED_OPEN_PARENTHESIS.
          get(definition, (reader.pos()-1), String.valueOf(c));
      throw new DecodeException(message);
    }


    // Skip over any spaces immediately following the opening parenthesis.
    reader.skipWhitespaces();

    // The next set of characters must be the OID.
    String oid = SchemaUtils.readNumericOID(reader);

    SortedSet<String> names = SchemaUtils.emptySortedSet();
    String description = "".intern();
    boolean isObsolete = false;
    String syntax = null;
    Map<String, List<String>> extraProperties = Collections.emptyMap();

    // At this point, we should have a pretty specific syntax that describes
    // what may come next, but some of the components are optional and it would
    // be pretty easy to put something in the wrong order, so we will be very
    // flexible about what we can accept.  Just look at the next token, figure
    // out what it is and how to treat what comes after it, then repeat until
    // we get to the end of the value.  But before we start, set default values
    // for everything else we might need to know.
    while (true)
    {
      String tokenName = SchemaUtils.readTokenName(reader);

      if (tokenName == null)
      {
        // No more tokens.
        break;
      }
      else if (tokenName.equalsIgnoreCase("name"))
      {
        names = SchemaUtils.readNameDescriptors(reader);
      }
      else if (tokenName.equalsIgnoreCase("desc"))
      {
        // This specifies the description for the matching rule.  It is an
        // arbitrary string of characters enclosed in single quotes.
        description = SchemaUtils.readQuotedString(reader);
      }
      else if (tokenName.equalsIgnoreCase("obsolete"))
      {
        // This indicates whether the matching rule should be considered
        // obsolete.  We do not need to do any more parsing for this token.
        isObsolete = true;
      }
      else if (tokenName.equalsIgnoreCase("syntax"))
      {
        syntax = SchemaUtils.readNumericOID(reader);
      }
      else
      {
        // This must be a non-standard property and it must be followed by
        // either a single value in single quotes or an open parenthesis
        // followed by one or more values in single quotes separated by spaces
        // followed by a close parenthesis.
        if(extraProperties == Collections.emptyList())
        {
          extraProperties = new HashMap<String, List<String>>();
        }
        extraProperties.put(tokenName,
            SchemaUtils.readExtraParameterValues(reader));
      }
    }

    // Make sure that a syntax was specified.
    if (syntax == null)
    {
      Message message = ERR_ATTR_SYNTAX_MR_NO_SYNTAX.get(definition);
      throw new DecodeException(message);
    }

    return new MatchingRule(oid, names, description, isObsolete, syntax,
        extraProperties, definition);
  }
}
