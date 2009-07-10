package org.opends.ldap;



import java.util.concurrent.ExecutionException;

import org.opends.ldap.responses.ErrorResultException;
import org.opends.ldap.responses.Result;



/**
 * Created by IntelliJ IDEA. User: digitalperk Date: Jun 1, 2009 Time:
 * 3:16:58 PM To change this template use File | Settings | File
 * Templates.
 */
public interface ResponseHandler<R extends Result>
{
  public void handleErrorResult(ErrorResultException result);



  public void handleException(ExecutionException e);



  public void handleResult(R result);
}
