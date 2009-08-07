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

package org.opends.spi;



import org.opends.ldap.requests.ExtendedRequest;
import org.opends.ldap.responses.Result;
import org.opends.server.types.ByteString;
import org.opends.util.Validator;



/**
 * An abstract Extended request which can be used as the basis for
 * implementing new Extended operations.
 *
 * @param <R>
 *          The type of Extended request.
 * @param <S>
 *          The type of result.
 */
public abstract class AbstractExtendedRequest<R extends ExtendedRequest<R, S>, S extends Result>
    extends AbstractMessage<R> implements ExtendedRequest<R, S>
{
  private String name;



  /**
   * Creates a new abstract extended request using the provided request
   * name.
   *
   * @param oid
   *          The dotted-decimal representation of the unique OID
   *          corresponding to this extended request.
   * @throws NullPointerException
   *           If {@code oid} was {@code null}.
   */
  protected AbstractExtendedRequest(String oid)
      throws NullPointerException
  {
    Validator.ensureNotNull(oid);

    this.name = oid;
  }



  /**
   * Returns the extended operation associated with this extended
   * request.
   * <p>
   * FIXME: this should not be exposed to clients.
   *
   * @return The extended operation associated with this extended
   *         request.
   */
  public abstract ExtendedOperation<R, S> getExtendedOperation();



  /**
   * {@inheritDoc}
   */
  public final String getRequestName()
  {
    return name;
  }



  /**
   * {@inheritDoc}
   */
  public abstract ByteString getRequestValue();



  /**
   * {@inheritDoc}
   */
  public final R setRequestName(String oid)
  {
    Validator.ensureNotNull(oid);

    this.name = oid;
    return getThis();
  }



  /**
   * {@inheritDoc}
   */
  public StringBuilder toString(StringBuilder builder)
      throws NullPointerException
  {
    builder.append("ExtendedRequest(requestName=");
    builder.append(name);
    builder.append(", requestValue=");
    ByteString value = getRequestValue();
    builder.append(value == null ? ByteString.empty() : value);
    builder.append(", controls=");
    builder.append(getControls());
    builder.append(")");
    return builder;
  }



  /**
   * Returns a type-safe reference to this request.
   *
   * @return This request as a T.
   */
  @SuppressWarnings("unchecked")
  private final R getThis()
  {
    return (R) this;
  }
}
