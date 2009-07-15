package org.opends.ldap.responses;



import org.opends.ldap.ResultCode;
import org.opends.server.types.ByteString;
import org.opends.server.util.Validator;
import org.opends.types.DN;



/**
 * Created by IntelliJ IDEA. User: digitalperk Date: May 25, 2009 Time:
 * 6:51:21 PM To change this template use File | Settings | File
 * Templates.
 */
public final class BindResult extends Result
{
  private ByteString serverSASLCreds;



  public BindResult(ResultCode resultCode, DN matchedDN,
      String diagnosticMessage)
  {
    super(resultCode, matchedDN.toString(), diagnosticMessage);
    serverSASLCreds = ByteString.empty();
  }



  public BindResult(ResultCode resultCode, String matchedDN,
      String diagnosticMessage)
  {
    super(resultCode, matchedDN, diagnosticMessage);
    serverSASLCreds = ByteString.empty();
  }



  public ByteString getServerSASLCreds()
  {
    return serverSASLCreds;
  }



  public BindResult setServerSASLCreds(ByteString serverSASLCreds)
  {
    Validator.ensureNotNull(serverSASLCreds);
    this.serverSASLCreds = serverSASLCreds;
    return this;
  }



  @Override
  public void toString(StringBuilder buffer)
  {
    buffer.append("BindResponse(resultCode=");
    buffer.append(getResultCode());
    buffer.append(", matchedDN=");
    buffer.append(getMatchedDN());
    buffer.append(", diagnosticMessage=");
    buffer.append(getDiagnosticMessage());
    buffer.append(", referrals=");
    buffer.append(getReferrals());
    buffer.append(", serverSASLCreds=");
    buffer.append(serverSASLCreds);
    buffer.append(", controls=");
    buffer.append(getControls());
    buffer.append(")");
  }
}
