//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.11 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2020.04.02 às 12:00:24 PM BRT 
//


package br.edu.exemploPizzaria.soapclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de cResultadoServicos complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="cResultadoServicos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ServicosCalculo" type="{http://tempuri.org/}ArrayOfCServicosCalculo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cResultadoServicos", propOrder = {
    "servicosCalculo"
})
public class CResultadoServicos {

    @XmlElement(name = "ServicosCalculo")
    protected ArrayOfCServicosCalculo servicosCalculo;

    /**
     * Obtém o valor da propriedade servicosCalculo.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCServicosCalculo }
     *     
     */
    public ArrayOfCServicosCalculo getServicosCalculo() {
        return servicosCalculo;
    }

    /**
     * Define o valor da propriedade servicosCalculo.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCServicosCalculo }
     *     
     */
    public void setServicosCalculo(ArrayOfCServicosCalculo value) {
        this.servicosCalculo = value;
    }

}
