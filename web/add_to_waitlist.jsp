<%@include file="templates/student_header.jsp"%> <p align="center">
	<label><font color="#e67e22" size="5"> Add to Waitlist </font></label>
			
	<form action="add_to_waitlist" method="post" name="waitlist_form" onsubmit="return false;">
		<div class="form-group">
					<label for="emailAddress"><font color="#e67e22" size="4">Email Address</font></label><br>
		 			<input type="text" style="width:350px;" class="form-control" id="emailAddress" placeholder="student@mavs.uta.edu">
				</div>
				<div>
					<label for="pname"><font color="#e67e22" size="4">Display Name</font></label><br>
		 			<input type="text" style="width:350px;" class="form-control" id="pname" placeholder="student name">
				</div>
				<input type="submit" value="submit" onclick="javascript:FormSubmit();">
	</form>			 	
	<label id="result"><font color="#e67e22" size="4"></font></label>
	<script> function FormSubmit(){
									var email = document.getElementById("emailAddress").value;
									var pname = document.getElementById("pname").value;
									var params = ('emailAddress='+email+'&pname='+pname);
									var xmlhttp;
									xmlhttp = new XMLHttpRequest();
									xmlhttp.onreadystatechange=function(){
										if (xmlhttp.readyState==4){
											document.getElementById("result").innerHTML = xmlhttp.responseText;	
										}
									}
									xmlhttp.open("POST","add_to_waitlist",true);
									xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
									xmlhttp.setRequestHeader("Content-length",params.length);
									xmlhttp.setRequestHeader("Connection","close");
									xmlhttp.send(params);
									document.getElementById("result").innerHTML = "Attempting to add user to waitlist....";
								}
								</script>
<%@include file="templates/footer.jsp"%></p>