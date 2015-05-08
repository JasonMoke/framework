
package com.test.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.test.client package. 
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

    private final static QName _FindUsersResponse_QNAME = new QName("http://server.test.com/", "findUsersResponse");
    private final static QName _Say_QNAME = new QName("http://server.test.com/", "say");
    private final static QName _SayUserName_QNAME = new QName("http://server.test.com/", "sayUserName");
    private final static QName _FindUsers_QNAME = new QName("http://server.test.com/", "findUsers");
    private final static QName _SayHello_QNAME = new QName("http://server.test.com/", "sayHello");
    private final static QName _SayHelloResponse_QNAME = new QName("http://server.test.com/", "sayHelloResponse");
    private final static QName _SayUserNameResponse_QNAME = new QName("http://server.test.com/", "sayUserNameResponse");
    private final static QName _SayResponse_QNAME = new QName("http://server.test.com/", "sayResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.test.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayResponse }
     * 
     */
    public SayResponse createSayResponse() {
        return new SayResponse();
    }

    /**
     * Create an instance of {@link SayUserNameResponse }
     * 
     */
    public SayUserNameResponse createSayUserNameResponse() {
        return new SayUserNameResponse();
    }

    /**
     * Create an instance of {@link SayUserName }
     * 
     */
    public SayUserName createSayUserName() {
        return new SayUserName();
    }

    /**
     * Create an instance of {@link Say }
     * 
     */
    public Say createSay() {
        return new Say();
    }

    /**
     * Create an instance of {@link FindUsersResponse }
     * 
     */
    public FindUsersResponse createFindUsersResponse() {
        return new FindUsersResponse();
    }

    /**
     * Create an instance of {@link SayHelloResponse }
     * 
     */
    public SayHelloResponse createSayHelloResponse() {
        return new SayHelloResponse();
    }

    /**
     * Create an instance of {@link SayHello }
     * 
     */
    public SayHello createSayHello() {
        return new SayHello();
    }

    /**
     * Create an instance of {@link FindUsers }
     * 
     */
    public FindUsers createFindUsers() {
        return new FindUsers();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link ListObject }
     * 
     */
    public ListObject createListObject() {
        return new ListObject();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.test.com/", name = "findUsersResponse")
    public JAXBElement<FindUsersResponse> createFindUsersResponse(FindUsersResponse value) {
        return new JAXBElement<FindUsersResponse>(_FindUsersResponse_QNAME, FindUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Say }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.test.com/", name = "say")
    public JAXBElement<Say> createSay(Say value) {
        return new JAXBElement<Say>(_Say_QNAME, Say.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayUserName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.test.com/", name = "sayUserName")
    public JAXBElement<SayUserName> createSayUserName(SayUserName value) {
        return new JAXBElement<SayUserName>(_SayUserName_QNAME, SayUserName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.test.com/", name = "findUsers")
    public JAXBElement<FindUsers> createFindUsers(FindUsers value) {
        return new JAXBElement<FindUsers>(_FindUsers_QNAME, FindUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.test.com/", name = "sayHello")
    public JAXBElement<SayHello> createSayHello(SayHello value) {
        return new JAXBElement<SayHello>(_SayHello_QNAME, SayHello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.test.com/", name = "sayHelloResponse")
    public JAXBElement<SayHelloResponse> createSayHelloResponse(SayHelloResponse value) {
        return new JAXBElement<SayHelloResponse>(_SayHelloResponse_QNAME, SayHelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayUserNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.test.com/", name = "sayUserNameResponse")
    public JAXBElement<SayUserNameResponse> createSayUserNameResponse(SayUserNameResponse value) {
        return new JAXBElement<SayUserNameResponse>(_SayUserNameResponse_QNAME, SayUserNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.test.com/", name = "sayResponse")
    public JAXBElement<SayResponse> createSayResponse(SayResponse value) {
        return new JAXBElement<SayResponse>(_SayResponse_QNAME, SayResponse.class, null, value);
    }

}
