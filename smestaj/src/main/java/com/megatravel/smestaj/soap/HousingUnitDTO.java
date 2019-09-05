//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.04 at 08:41:40 PM CEST 
//


package com.megatravel.smestaj.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HousingUnitDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HousingUnitDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="agent" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="allowedPeople" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="daysForCancelling" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="allowedCancelling" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="address" type="{http://www.megatravel.com/smestaj/soap}AddressDTO"/>
 *         &lt;element name="type" type="{http://www.megatravel.com/smestaj/soap}TypeDTO"/>
 *         &lt;element name="options" type="{http://www.megatravel.com/smestaj/soap}OptionDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HousingUnitDTO", propOrder = {
    "id",
    "agent",
    "description",
    "allowedPeople",
    "daysForCancelling",
    "allowedCancelling",
    "price",
    "rating",
    "address",
    "type",
    "options"
})
public class HousingUnitDTO {

    protected long id;
    protected long agent;
    @XmlElement(required = true)
    protected String description;
    protected int allowedPeople;
    protected int daysForCancelling;
    protected boolean allowedCancelling;
    protected double price;
    protected double rating;
    @XmlElement(required = true)
    protected AddressDTO address;
    @XmlElement(required = true)
    protected TypeDTO type;
    protected List<OptionDTO> options;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the agent property.
     * 
     */
    public long getAgent() {
        return agent;
    }

    /**
     * Sets the value of the agent property.
     * 
     */
    public void setAgent(long value) {
        this.agent = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the allowedPeople property.
     * 
     */
    public int getAllowedPeople() {
        return allowedPeople;
    }

    /**
     * Sets the value of the allowedPeople property.
     * 
     */
    public void setAllowedPeople(int value) {
        this.allowedPeople = value;
    }

    /**
     * Gets the value of the daysForCancelling property.
     * 
     */
    public int getDaysForCancelling() {
        return daysForCancelling;
    }

    /**
     * Sets the value of the daysForCancelling property.
     * 
     */
    public void setDaysForCancelling(int value) {
        this.daysForCancelling = value;
    }

    /**
     * Gets the value of the allowedCancelling property.
     * 
     */
    public boolean isAllowedCancelling() {
        return allowedCancelling;
    }

    /**
     * Sets the value of the allowedCancelling property.
     * 
     */
    public void setAllowedCancelling(boolean value) {
        this.allowedCancelling = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     */
    public void setRating(double value) {
        this.rating = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AddressDTO }
     *     
     */
    public AddressDTO getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressDTO }
     *     
     */
    public void setAddress(AddressDTO value) {
        this.address = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link TypeDTO }
     *     
     */
    public TypeDTO getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeDTO }
     *     
     */
    public void setType(TypeDTO value) {
        this.type = value;
    }

    /**
     * Gets the value of the options property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the options property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OptionDTO }
     * 
     * 
     */
    public List<OptionDTO> getOptions() {
        if (options == null) {
            options = new ArrayList<OptionDTO>();
        }
        return this.options;
    }

}
