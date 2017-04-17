<%-- 
    Document   : frgtpass
    Created on : Apr 14, 2017, 3:18:50 PM
    Author     : Pooja Endapally
--%>


<%@include file="templates/header.jsp"%>
	<div class="container">
        <h1>PASSWORD</h1>
      <form action="frgtpass" method="post">
	<div class="row">
	<div class="col-md-4 col-lg-4">
		<div class="form-group">
			
			 	
			 <label for="emailAddress">Email Address</label>
			 <input type="text" class="form-control" name=emailAddress
			 placeholder="firstname.lastname@mavs.uta.edu">  
                         <label for="userid">New Password</label>
			 <input type="password" class="form-control" name=pass>
                         <label for="userid">Security Question: What is your favourite colour?</label>
                         <input type="text" class="form-control" name=sec>
              
		</div>
	</div>
	</div>
	<button type="submit" class="btn btn-primary">Submit</button></p>	
<%@include file="templates/footer.jsp"%>
          
      
   