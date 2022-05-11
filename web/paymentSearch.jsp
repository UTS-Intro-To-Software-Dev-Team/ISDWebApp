<%-- 
    Document   : paymentSearch
    Created on : 11 May 2022, 11:04:38 am
    Author     : nexusbaquir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Payment"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="PageComponents/CommonMeta.jsp"/>
        <title>Payment Search</title>
    </head>
    
    <jsp:include page="PageComponents/JSPHeader.jsp"/>
    
    <body>
        <h1>Search Payment Records</h1>
        <h4>Search specific payment records based on  Payment ID and date paid.</h4>
        
        <%              
            Payment searchPayment = (Payment) session.getAttribute("searchPayment");           
        %>
        
         <form action="Payment_SearchServlet" method="post">
            
        <%-- <p>#${paymentId} </p>--%>
        
        <table class="align-center form-table">
            <tr> <td><label for name="search_paymentId" class="subtitle">Payment ID </label></td>
                 <td><input type ="text" id="searchPaymentId" name="search_paymentId" placeholder= "Search by Payment ID" required ></td>
            </tr>
                
                <tr> <td><label for name="search_datePaid" class="subtitle">Date Paid </label></td>
                    <td><input type ="date" id="searchDatePaid" name="search_datePaid" placeholder= "Search by Date Paid" required ></td>
                </tr>
                <tr><td><button type="submit" value="PaymentDetails"><b>Submit</b></button></td></tr>
        </table>
        </form>
        <% if(searchPayment != null){ %>
            <b><p>Payment Details</p></b>
            <table>
                <thead>
                    <tr>
                        <th><b>Payment Id</b></th>
                        <th><b>Date Paid</b></th>
                        <th><b>Payment Method</b></th>
                        <th><b>Card Number</b></th>
                        <th><b>CVV</b></th>
                        <th><b>Name On Card</b></th>
                        <th><b>Expiry Date</b></th>
                    </tr>
                </thead>
                <%--
                <tbody> 
                    <tr>
                        <td><p><%=searchPayment.getDatePaid()%></p></td>
                        <td><p><%=searchPayment.getPaymentMethod()%></p></td>
                        <td><p><%=searchPayment.getCardNumber()%></p></td>
                        <td><p><%=searchPayment.getCvv()%></p></td>
                        <td><p><%=searchPayment.getNameOnCard()%></p></td>
                        <td><p><%=searchPayment.getExpiryDate()%></p></td>
                    </tr> 
                </tbody>
                --%>
                
            </table> 
                
        <%  } else { %>
            <span><%=(searchPayment != null ? searchPayment : "")%></span>
        <% } %>
            
    </body>
</html>

