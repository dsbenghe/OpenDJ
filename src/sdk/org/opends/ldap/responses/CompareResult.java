package org.opends.ldap.responses;



import org.opends.ldap.ResultCode;
import org.opends.types.DN;



/**
 * Created by IntelliJ IDEA. User: digitalperk Date: May 26, 2009 Time:
 * 10:12:23 AM To change this template use File | Settings | File
 * Templates.
 */
public final class CompareResult extends Result
{
  public CompareResult(ResultCode resultCode, DN matchedDN,
      String diagnosticMessage)
  {
    super(resultCode, matchedDN.toString(), diagnosticMessage);
  }



  public CompareResult(ResultCode resultCode, String matchedDN,
      String diagnosticMessage)
  {
    super(resultCode, matchedDN, diagnosticMessage);
  }



  @Override
  public void toString(StringBuilder buffer)
  {
    buffer.append("CompareResponse(resultCode=");
    buffer.append(getResultCode());
    buffer.append(", matchedDN=");
    buffer.append(getMatchedDN());
    buffer.append(", diagnosticMessage=");
    buffer.append(getDiagnosticMessage());
    buffer.append(", referrals=");
    buffer.append(getReferrals());
    buffer.append(", controls=");
    buffer.append(getControls());
    buffer.append(")");
  }
}
