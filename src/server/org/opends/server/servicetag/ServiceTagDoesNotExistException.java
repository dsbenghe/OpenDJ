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
 *      Copyright 2008 Sun Microsystems, Inc.
 */
package org.opends.server.servicetag;

import org.opends.messages.Message;

/**
 * Exception thrown when the ServiceTag does not exist.
 */
public class ServiceTagDoesNotExistException extends ServiceTagException {

   /**
   * The serial version identifier required to satisfy the compiler
   * because this class extends <CODE>java.lang.Exception</CODE>,
   * which implements the <CODE>java.io.Serializable</CODE> interface.
   * This value was generated using the <CODE>serialver</CODE>
   * command-line utility included with the Java SDK.
   */
    private static final long serialVersionUID = -134526464852686155L;

    /**
     * Create a ServiceTag Exception with a message and cause.
     * @param message
     *          The message.
     */
    protected ServiceTagDoesNotExistException(Message message) {
        super(message);
    }

    /**
     * Create a ServiceTag Exception with a message and cause.
     * @param message
     *          The message.
     * @param cause
     *          The cause.
     */
    protected ServiceTagDoesNotExistException(
            Message message, Throwable cause) {
        super(message, cause);
    }
}
