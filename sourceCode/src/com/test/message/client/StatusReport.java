
package com.test.message.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>statusReport complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="statusReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reportStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seqID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="serviceCodeAdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submitDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statusReport", propOrder = {
    "errorCode",
    "memo",
    "mobile",
    "receiveDate",
    "reportStatus",
    "seqID",
    "serviceCodeAdd",
    "submitDate"
})
public class StatusReport {

    protected String errorCode;
    protected String memo;
    protected String mobile;
    protected String receiveDate;
    protected int reportStatus;
    protected long seqID;
    protected String serviceCodeAdd;
    protected String submitDate;

    /**
     * 获取errorCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 设置errorCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * 获取memo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置memo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
    }

    /**
     * 获取mobile属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置mobile属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * 获取receiveDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiveDate() {
        return receiveDate;
    }

    /**
     * 设置receiveDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiveDate(String value) {
        this.receiveDate = value;
    }

    /**
     * 获取reportStatus属性的值。
     * 
     */
    public int getReportStatus() {
        return reportStatus;
    }

    /**
     * 设置reportStatus属性的值。
     * 
     */
    public void setReportStatus(int value) {
        this.reportStatus = value;
    }

    /**
     * 获取seqID属性的值。
     * 
     */
    public long getSeqID() {
        return seqID;
    }

    /**
     * 设置seqID属性的值。
     * 
     */
    public void setSeqID(long value) {
        this.seqID = value;
    }

    /**
     * 获取serviceCodeAdd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCodeAdd() {
        return serviceCodeAdd;
    }

    /**
     * 设置serviceCodeAdd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCodeAdd(String value) {
        this.serviceCodeAdd = value;
    }

    /**
     * 获取submitDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmitDate() {
        return submitDate;
    }

    /**
     * 设置submitDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmitDate(String value) {
        this.submitDate = value;
    }

}
