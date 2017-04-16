<%@include file="templates/header.jsp"%>
	
	<BODY>
        <H1>Feedback</H1>
        
        <form action="feedbacksubmit" METHOD="POST">
            Please enter your feedback:
            <BR>
            <TEXTAREA NAME="feedback" ROWS="5"></TEXTAREA>
            <BR>
            <INPUT TYPE="SUBMIT" VALUE="Submit" >
        </form>
    </BODY>
	
<%@include file="templates/footer.jsp"%>