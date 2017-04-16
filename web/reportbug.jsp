<%@include file="templates/header.jsp"%>
	
	<BODY>
        <H1>Report bug</H1>
        
        <form action="reportbugsubmit" METHOD="POST">
            Please enter your bug:
            <BR>
            <TEXTAREA NAME="feedback" ROWS="5"></TEXTAREA>
            <BR>
            <INPUT TYPE="SUBMIT" VALUE="Submit" >
        </form>
    </BODY>
	
<%@include file="templates/footer.jsp"%>