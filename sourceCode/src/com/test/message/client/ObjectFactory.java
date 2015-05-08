
package com.test.message.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.test.message.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetBalanceResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "getBalanceResponse");
    private final static QName _CancelMOForwardResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "cancelMOForwardResponse");
    private final static QName _GetReportResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "getReportResponse");
    private final static QName _ChargeUpResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "chargeUpResponse");
    private final static QName _GetMOResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "getMOResponse");
    private final static QName _SendVoiceResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "sendVoiceResponse");
    private final static QName _SerialPwdUpdResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "serialPwdUpdResponse");
    private final static QName _Logout_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "logout");
    private final static QName _GetMO_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "getMO");
    private final static QName _ChargeUp_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "chargeUp");
    private final static QName _RegistDetailInfoResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "registDetailInfoResponse");
    private final static QName _SendSMSResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "sendSMSResponse");
    private final static QName _GetEachFeeResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "getEachFeeResponse");
    private final static QName _CancelMOForward_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "cancelMOForward");
    private final static QName _SetMOForwardEx_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "setMOForwardEx");
    private final static QName _SendSMS_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "sendSMS");
    private final static QName _SendVoice_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "sendVoice");
    private final static QName _SerialPwdUpd_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "serialPwdUpd");
    private final static QName _GetEachFee_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "getEachFee");
    private final static QName _GetVersionResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "getVersionResponse");
    private final static QName _SetMOForward_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "setMOForward");
    private final static QName _RegistExResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "registExResponse");
    private final static QName _RegistEx_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "registEx");
    private final static QName _GetVersion_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "getVersion");
    private final static QName _RegistDetailInfo_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "registDetailInfo");
    private final static QName _GetBalance_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "getBalance");
    private final static QName _SetMOForwardExResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "setMOForwardExResponse");
    private final static QName _GetReport_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "getReport");
    private final static QName _LogoutResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "logoutResponse");
    private final static QName _SetMOForwardResponse_QNAME = new QName("http://sdkhttp.eucp.b2m.cn/", "setMOForwardResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.test.message.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetEachFee }
     * 
     */
    public GetEachFee createGetEachFee() {
        return new GetEachFee();
    }

    /**
     * Create an instance of {@link SendVoice }
     * 
     */
    public SendVoice createSendVoice() {
        return new SendVoice();
    }

    /**
     * Create an instance of {@link SerialPwdUpd }
     * 
     */
    public SerialPwdUpd createSerialPwdUpd() {
        return new SerialPwdUpd();
    }

    /**
     * Create an instance of {@link SendSMS }
     * 
     */
    public SendSMS createSendSMS() {
        return new SendSMS();
    }

    /**
     * Create an instance of {@link SetMOForward }
     * 
     */
    public SetMOForward createSetMOForward() {
        return new SetMOForward();
    }

    /**
     * Create an instance of {@link GetVersionResponse }
     * 
     */
    public GetVersionResponse createGetVersionResponse() {
        return new GetVersionResponse();
    }

    /**
     * Create an instance of {@link RegistDetailInfo }
     * 
     */
    public RegistDetailInfo createRegistDetailInfo() {
        return new RegistDetailInfo();
    }

    /**
     * Create an instance of {@link GetBalance }
     * 
     */
    public GetBalance createGetBalance() {
        return new GetBalance();
    }

    /**
     * Create an instance of {@link GetVersion }
     * 
     */
    public GetVersion createGetVersion() {
        return new GetVersion();
    }

    /**
     * Create an instance of {@link RegistEx }
     * 
     */
    public RegistEx createRegistEx() {
        return new RegistEx();
    }

    /**
     * Create an instance of {@link RegistExResponse }
     * 
     */
    public RegistExResponse createRegistExResponse() {
        return new RegistExResponse();
    }

    /**
     * Create an instance of {@link SetMOForwardResponse }
     * 
     */
    public SetMOForwardResponse createSetMOForwardResponse() {
        return new SetMOForwardResponse();
    }

    /**
     * Create an instance of {@link SetMOForwardExResponse }
     * 
     */
    public SetMOForwardExResponse createSetMOForwardExResponse() {
        return new SetMOForwardExResponse();
    }

    /**
     * Create an instance of {@link GetReport }
     * 
     */
    public GetReport createGetReport() {
        return new GetReport();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link GetReportResponse }
     * 
     */
    public GetReportResponse createGetReportResponse() {
        return new GetReportResponse();
    }

    /**
     * Create an instance of {@link CancelMOForwardResponse }
     * 
     */
    public CancelMOForwardResponse createCancelMOForwardResponse() {
        return new CancelMOForwardResponse();
    }

    /**
     * Create an instance of {@link GetBalanceResponse }
     * 
     */
    public GetBalanceResponse createGetBalanceResponse() {
        return new GetBalanceResponse();
    }

    /**
     * Create an instance of {@link GetMOResponse }
     * 
     */
    public GetMOResponse createGetMOResponse() {
        return new GetMOResponse();
    }

    /**
     * Create an instance of {@link SendVoiceResponse }
     * 
     */
    public SendVoiceResponse createSendVoiceResponse() {
        return new SendVoiceResponse();
    }

    /**
     * Create an instance of {@link ChargeUpResponse }
     * 
     */
    public ChargeUpResponse createChargeUpResponse() {
        return new ChargeUpResponse();
    }

    /**
     * Create an instance of {@link GetMO }
     * 
     */
    public GetMO createGetMO() {
        return new GetMO();
    }

    /**
     * Create an instance of {@link ChargeUp }
     * 
     */
    public ChargeUp createChargeUp() {
        return new ChargeUp();
    }

    /**
     * Create an instance of {@link RegistDetailInfoResponse }
     * 
     */
    public RegistDetailInfoResponse createRegistDetailInfoResponse() {
        return new RegistDetailInfoResponse();
    }

    /**
     * Create an instance of {@link GetEachFeeResponse }
     * 
     */
    public GetEachFeeResponse createGetEachFeeResponse() {
        return new GetEachFeeResponse();
    }

    /**
     * Create an instance of {@link SendSMSResponse }
     * 
     */
    public SendSMSResponse createSendSMSResponse() {
        return new SendSMSResponse();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link SerialPwdUpdResponse }
     * 
     */
    public SerialPwdUpdResponse createSerialPwdUpdResponse() {
        return new SerialPwdUpdResponse();
    }

    /**
     * Create an instance of {@link SetMOForwardEx }
     * 
     */
    public SetMOForwardEx createSetMOForwardEx() {
        return new SetMOForwardEx();
    }

    /**
     * Create an instance of {@link CancelMOForward }
     * 
     */
    public CancelMOForward createCancelMOForward() {
        return new CancelMOForward();
    }

    /**
     * Create an instance of {@link Mo }
     * 
     */
    public Mo createMo() {
        return new Mo();
    }

    /**
     * Create an instance of {@link StatusReport }
     * 
     */
    public StatusReport createStatusReport() {
        return new StatusReport();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBalanceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "getBalanceResponse")
    public JAXBElement<GetBalanceResponse> createGetBalanceResponse(GetBalanceResponse value) {
        return new JAXBElement<GetBalanceResponse>(_GetBalanceResponse_QNAME, GetBalanceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelMOForwardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "cancelMOForwardResponse")
    public JAXBElement<CancelMOForwardResponse> createCancelMOForwardResponse(CancelMOForwardResponse value) {
        return new JAXBElement<CancelMOForwardResponse>(_CancelMOForwardResponse_QNAME, CancelMOForwardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReportResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "getReportResponse")
    public JAXBElement<GetReportResponse> createGetReportResponse(GetReportResponse value) {
        return new JAXBElement<GetReportResponse>(_GetReportResponse_QNAME, GetReportResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeUpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "chargeUpResponse")
    public JAXBElement<ChargeUpResponse> createChargeUpResponse(ChargeUpResponse value) {
        return new JAXBElement<ChargeUpResponse>(_ChargeUpResponse_QNAME, ChargeUpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMOResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "getMOResponse")
    public JAXBElement<GetMOResponse> createGetMOResponse(GetMOResponse value) {
        return new JAXBElement<GetMOResponse>(_GetMOResponse_QNAME, GetMOResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendVoiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "sendVoiceResponse")
    public JAXBElement<SendVoiceResponse> createSendVoiceResponse(SendVoiceResponse value) {
        return new JAXBElement<SendVoiceResponse>(_SendVoiceResponse_QNAME, SendVoiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SerialPwdUpdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "serialPwdUpdResponse")
    public JAXBElement<SerialPwdUpdResponse> createSerialPwdUpdResponse(SerialPwdUpdResponse value) {
        return new JAXBElement<SerialPwdUpdResponse>(_SerialPwdUpdResponse_QNAME, SerialPwdUpdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "logout")
    public JAXBElement<Logout> createLogout(Logout value) {
        return new JAXBElement<Logout>(_Logout_QNAME, Logout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "getMO")
    public JAXBElement<GetMO> createGetMO(GetMO value) {
        return new JAXBElement<GetMO>(_GetMO_QNAME, GetMO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChargeUp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "chargeUp")
    public JAXBElement<ChargeUp> createChargeUp(ChargeUp value) {
        return new JAXBElement<ChargeUp>(_ChargeUp_QNAME, ChargeUp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistDetailInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "registDetailInfoResponse")
    public JAXBElement<RegistDetailInfoResponse> createRegistDetailInfoResponse(RegistDetailInfoResponse value) {
        return new JAXBElement<RegistDetailInfoResponse>(_RegistDetailInfoResponse_QNAME, RegistDetailInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "sendSMSResponse")
    public JAXBElement<SendSMSResponse> createSendSMSResponse(SendSMSResponse value) {
        return new JAXBElement<SendSMSResponse>(_SendSMSResponse_QNAME, SendSMSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEachFeeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "getEachFeeResponse")
    public JAXBElement<GetEachFeeResponse> createGetEachFeeResponse(GetEachFeeResponse value) {
        return new JAXBElement<GetEachFeeResponse>(_GetEachFeeResponse_QNAME, GetEachFeeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelMOForward }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "cancelMOForward")
    public JAXBElement<CancelMOForward> createCancelMOForward(CancelMOForward value) {
        return new JAXBElement<CancelMOForward>(_CancelMOForward_QNAME, CancelMOForward.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMOForwardEx }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "setMOForwardEx")
    public JAXBElement<SetMOForwardEx> createSetMOForwardEx(SetMOForwardEx value) {
        return new JAXBElement<SetMOForwardEx>(_SetMOForwardEx_QNAME, SetMOForwardEx.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "sendSMS")
    public JAXBElement<SendSMS> createSendSMS(SendSMS value) {
        return new JAXBElement<SendSMS>(_SendSMS_QNAME, SendSMS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendVoice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "sendVoice")
    public JAXBElement<SendVoice> createSendVoice(SendVoice value) {
        return new JAXBElement<SendVoice>(_SendVoice_QNAME, SendVoice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SerialPwdUpd }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "serialPwdUpd")
    public JAXBElement<SerialPwdUpd> createSerialPwdUpd(SerialPwdUpd value) {
        return new JAXBElement<SerialPwdUpd>(_SerialPwdUpd_QNAME, SerialPwdUpd.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEachFee }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "getEachFee")
    public JAXBElement<GetEachFee> createGetEachFee(GetEachFee value) {
        return new JAXBElement<GetEachFee>(_GetEachFee_QNAME, GetEachFee.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVersionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "getVersionResponse")
    public JAXBElement<GetVersionResponse> createGetVersionResponse(GetVersionResponse value) {
        return new JAXBElement<GetVersionResponse>(_GetVersionResponse_QNAME, GetVersionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMOForward }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "setMOForward")
    public JAXBElement<SetMOForward> createSetMOForward(SetMOForward value) {
        return new JAXBElement<SetMOForward>(_SetMOForward_QNAME, SetMOForward.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistExResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "registExResponse")
    public JAXBElement<RegistExResponse> createRegistExResponse(RegistExResponse value) {
        return new JAXBElement<RegistExResponse>(_RegistExResponse_QNAME, RegistExResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistEx }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "registEx")
    public JAXBElement<RegistEx> createRegistEx(RegistEx value) {
        return new JAXBElement<RegistEx>(_RegistEx_QNAME, RegistEx.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVersion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "getVersion")
    public JAXBElement<GetVersion> createGetVersion(GetVersion value) {
        return new JAXBElement<GetVersion>(_GetVersion_QNAME, GetVersion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistDetailInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "registDetailInfo")
    public JAXBElement<RegistDetailInfo> createRegistDetailInfo(RegistDetailInfo value) {
        return new JAXBElement<RegistDetailInfo>(_RegistDetailInfo_QNAME, RegistDetailInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBalance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "getBalance")
    public JAXBElement<GetBalance> createGetBalance(GetBalance value) {
        return new JAXBElement<GetBalance>(_GetBalance_QNAME, GetBalance.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMOForwardExResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "setMOForwardExResponse")
    public JAXBElement<SetMOForwardExResponse> createSetMOForwardExResponse(SetMOForwardExResponse value) {
        return new JAXBElement<SetMOForwardExResponse>(_SetMOForwardExResponse_QNAME, SetMOForwardExResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "getReport")
    public JAXBElement<GetReport> createGetReport(GetReport value) {
        return new JAXBElement<GetReport>(_GetReport_QNAME, GetReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "logoutResponse")
    public JAXBElement<LogoutResponse> createLogoutResponse(LogoutResponse value) {
        return new JAXBElement<LogoutResponse>(_LogoutResponse_QNAME, LogoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMOForwardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sdkhttp.eucp.b2m.cn/", name = "setMOForwardResponse")
    public JAXBElement<SetMOForwardResponse> createSetMOForwardResponse(SetMOForwardResponse value) {
        return new JAXBElement<SetMOForwardResponse>(_SetMOForwardResponse_QNAME, SetMOForwardResponse.class, null, value);
    }

}
