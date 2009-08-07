/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE
 * or https://OpenDS.dev.java.net/OpenDS.LICENSE.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE.  If applicable,
 * add the following below this CDDL HEADER, with the fields enclosed
 * by brackets "[]" replaced with your own identifying information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Copyright 2009 Sun Microsystems, Inc.
 */

package org.opends.ldap.requests;



import java.util.Collection;

import org.opends.types.DereferenceAliasesPolicy;
import org.opends.types.SearchScope;
import org.opends.types.filter.Filter;



/**
 * A Search request. The Search operation is used to request a server to
 * return, subject to access controls and other restrictions, a set of
 * entries matching a complex search criterion. This can be used to read
 * attributes from a single entry, from entries immediately subordinate
 * to a particular entry, or from a whole subtree of entries.
 */
public interface SearchRequest extends Request<SearchRequest>
{

  /**
   * Adds the provided attribute names to the list of attributes to be
   * included with each entry that matches the search criteria.
   * Attributes that are sub-types of listed attributes are implicitly
   * included.
   * 
   * @param attributeDescriptions
   *          The names of the attributes to be included with each
   *          entry.
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit attribute names to
   *           be added.
   * @throws NullPointerException
   *           If {@code attributeDescriptions} was {@code null}, or if
   *           it contained a {@code null} element.
   */
  SearchRequest addAttribute(Collection<String> attributeDescriptions)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * Adds the provided attribute name to the list of attributes to be
   * included with each entry that matches the search criteria.
   * Attributes that are sub-types of listed attributes are implicitly
   * included.
   * 
   * @param attributeDescription
   *          The name of the attribute to be included with each entry.
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit attribute names to
   *           be added.
   * @throws NullPointerException
   *           If {@code attributeDescription} was {@code null}.
   */
  SearchRequest addAttribute(String attributeDescription)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * Adds the provided attribute names to the list of attributes to be
   * included with each entry that matches the search criteria.
   * Attributes that are sub-types of listed attributes are implicitly
   * included.
   * 
   * @param attributeDescriptions
   *          The names of the attributes to be included with each
   *          entry.
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit attribute names to
   *           be added.
   * @throws NullPointerException
   *           If {@code attributeDescriptions} was {@code null}, or if
   *           it contained a {@code null} element.
   */
  SearchRequest addAttribute(String... attributeDescriptions)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * Clears the list of attributes to be included with each entry that
   * matches the search criteria. Attributes that are sub-types of
   * listed attributes are implicitly included.
   * 
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit attributes to be
   *           removed.
   */
  SearchRequest clearAttributes() throws UnsupportedOperationException;



  /**
   * Returns an {@code Iterable} containing the list of attributes to be
   * included with each entry that matches the search criteria.
   * Attributes that are sub-types of listed attributes are implicitly
   * included. The returned {@code Iterable} may be used to remove
   * attribute names if permitted by this search request.
   * 
   * @return An {@code Iterable} containing the list of attributes to be
   *         included with each entry.
   */
  Iterable<String> getAttributes();



  /**
   * Returns the name of the base entry relative to which the search is
   * to be performed.
   * 
   * @return The name of the base entry relative to which the search is
   *         to be performed.
   */
  String getBaseDN();



  /**
   * Returns an indication as to whether or not alias entries are to be
   * dereferenced during the search.
   * 
   * @return An indication as to whether or not alias entries are to be
   *         dereferenced during the search.
   */
  DereferenceAliasesPolicy getDereferenceAliases();



  /**
   * Returns the filter that defines the conditions that must be
   * fulfilled in order for an entry to be returned.
   * 
   * @return The filter that defines the conditions that must be
   *         fulfilled in order for an entry to be returned.
   */
  Filter getFilter();



  /**
   * Returns the scope of the search.
   * 
   * @return The scope of the search.
   */
  SearchScope getScope();



  /**
   * Returns the size limit that should be used in order to restrict the
   * maximum number of entries returned by the search.
   * <p>
   * A value of zero (the default) in this field indicates that no
   * client-requested size limit restrictions are in effect. Servers may
   * also enforce a maximum number of entries to return.
   * 
   * @return The size limit that should be used in order to restrict the
   *         maximum number of entries returned by the search.
   */
  int getSizeLimit();



  /**
   * Returns the time limit that should be used in order to restrict the
   * maximum time (in seconds) allowed for the search.
   * <p>
   * A value of zero (the default) in this field indicates that no
   * client-requested time limit restrictions are in effect for the
   * search. Servers may also enforce a maximum time limit for the
   * search.
   * 
   * @return The time limit that should be used in order to restrict the
   *         maximum time (in seconds) allowed for the search.
   */
  int getTimeLimit();



  /**
   * Indicates whether or not this search request has a list of
   * attributes to be included with each entry that matches the search
   * criteria.
   * 
   * @return {@code true} if this search request has a list of
   *         attributes to be included with each entry that matches the
   *         search criteria, otherwise {@code false}.
   */
  boolean hasAttributes();



  /**
   * Indicates whether search results are to contain both attribute
   * descriptions and values, or just attribute descriptions.
   * 
   * @return {@code true} if only attribute descriptions (and not
   *         values) are to be returned, or {@code false} (the default)
   *         if both attribute descriptions and values are to be
   *         returned.
   */
  boolean isTypesOnly();



  /**
   * Removes the provided attribute name from the list of attributes to
   * be included with each entry that matches the search criteria.
   * Attributes that are sub-types of listed attributes are implicitly
   * included.
   * 
   * @param attributeDescription
   *          The name of the attribute to be removed.
   * @return {@code true} if the attribute name was found in the list of
   *         attributes.
   * @throws UnsupportedOperationException
   *           If this search request does not permit attribute names to
   *           be removed.
   * @throws NullPointerException
   *           If {@code attributeDescription} was {@code null}.
   */
  boolean removeAttribute(String attributeDescription)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * Sets the name of the base entry relative to which the search is to
   * be performed.
   * 
   * @param dn
   *          The name of the base entry relative to which the search is
   *          to be performed.
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit the base DN to be
   *           set.
   * @throws NullPointerException
   *           If {@code dn} was {@code null}.
   */
  SearchRequest setBaseDN(String dn)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * Sets the alias dereferencing policy to be used during the search.
   * 
   * @param policy
   *          The alias dereferencing policy to be used during the
   *          search.
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit the alias
   *           dereferencing policy to be set.
   * @throws NullPointerException
   *           If {@code policy} was {@code null}.
   */
  SearchRequest setDereferenceAliases(DereferenceAliasesPolicy policy)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * Sets the filter that defines the conditions that must be fulfilled
   * in order for an entry to be returned.
   * 
   * @param filter
   *          The filter that defines the conditions that must be
   *          fulfilled in order for an entry to be returned.
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit the filter to be
   *           set.
   * @throws NullPointerException
   *           If {@code filter} was {@code null}.
   */
  SearchRequest setFilter(Filter filter)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * Sets the filter that defines the conditions that must be fulfilled
   * in order for an entry to be returned.
   * 
   * @param filter
   *          The filter that defines the conditions that must be
   *          fulfilled in order for an entry to be returned.
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit the filter to be
   *           set.
   * @throws IllegalArgumentException
   *           If {@code filter} is not a valid LDAP string
   *           representation of a filter.
   * @throws NullPointerException
   *           If {@code filter} was {@code null}.
   */
  SearchRequest setFilter(String filter)
      throws UnsupportedOperationException, IllegalArgumentException,
      NullPointerException;



  /**
   * Sets the scope of the search.
   * 
   * @param scope
   *          The scope of the search.
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit the scope to be
   *           set.
   * @throws NullPointerException
   *           If {@code scope} was {@code null}.
   */
  SearchRequest setScope(SearchScope scope)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * Sets the size limit that should be used in order to restrict the
   * maximum number of entries returned by the search.
   * <p>
   * A value of zero (the default) in this field indicates that no
   * client-requested size limit restrictions are in effect. Servers may
   * also enforce a maximum number of entries to return.
   * 
   * @param limit
   *          The size limit that should be used in order to restrict
   *          the maximum number of entries returned by the search.
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit the size limit to
   *           be set.
   * @throws IllegalArgumentException
   *           If {@code limit} was negative.
   */
  SearchRequest setSizeLimit(int limit)
      throws UnsupportedOperationException, IllegalArgumentException;



  /**
   * Sets the time limit that should be used in order to restrict the
   * maximum time (in seconds) allowed for the search.
   * <p>
   * A value of zero (the default) in this field indicates that no
   * client-requested time limit restrictions are in effect for the
   * search. Servers may also enforce a maximum time limit for the
   * search.
   * 
   * @param limit
   *          The time limit that should be used in order to restrict
   *          the maximum time (in seconds) allowed for the search.
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit the time limit to
   *           be set.
   * @throws IllegalArgumentException
   *           If {@code limit} was negative.
   */
  SearchRequest setTimeLimit(int limit)
      throws UnsupportedOperationException, IllegalArgumentException;



  /**
   * Specifies whether search results are to contain both attribute
   * descriptions and values, or just attribute descriptions.
   * 
   * @param typesOnly
   *          {@code true} if only attribute descriptions (and not
   *          values) are to be returned, or {@code false} (the default)
   *          if both attribute descriptions and values are to be
   *          returned.
   * @return This search request.
   * @throws UnsupportedOperationException
   *           If this search request does not permit the types-only
   *           parameter to be set.
   */
  SearchRequest setTypesOnly(boolean typesOnly)
      throws UnsupportedOperationException;
}