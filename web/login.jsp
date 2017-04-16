<%@ include file="templates/header.jsp" %>
<style>
.panel-heading {
    padding: 5px 15px;
}

.panel-footer {
	padding: 1px 15px;
	color: #A0A0A0;
}

.profile-img {
	width: 96px;
	height: 96px;
	margin: 0 auto 10px;
	display: block;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
}
</style>


   <%
    Cookie[] cookies=request.getCookies();
    String userName = "", password = "",rememberVal="";
    if (cookies != null) {
         for (Cookie cookie : cookies) {
           if(cookie.getName().equals("cookuser")) {
             userName = cookie.getValue();
           }
           if(cookie.getName().equals("cookpass")){
             password = cookie.getValue();
           }
           if(cookie.getName().equals("cookrem")){
             rememberVal = cookie.getValue();
           }
        }
    }
%>

 <div class="container" style="margin-top:40px">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Sign in to continue</strong>
					</div>
					<div class="panel-body">
						<form role="form" action="login" method="POST">
							<fieldset>
								<div class="row">
									<div class="center-block">

									
								</div>
								</div>
								<div class="row">
									<div class="col-sm-12 col-md-10  col-md-offset-1 ">
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-user"></i>
												</span> 
												<input type="text" class="form-control" name=emailAddress placeholder="yourname@mavs.uta.edu">
											</div>
										</div>
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-lock"></i>
												</span>
												<input type="password" class="form-control" name=password>
											</div>
										</div>
										 <p>
                    <label>Remember</label> <input type="checkbox" name="remember"
                        value="1"
                        <%= "1".equals(rememberVal.trim()) ? "checked=\"checked\"" : "" %> />
                </p>
										<div class="form-group">
											<input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign in">
										</div>
										
									</div>
								</div>
							</fieldset>
						</form>
						<form action="#">
						<div>
						<div><p align="right"><h1><p align="center">
										<a href="http://www.uta.edu/">UTA<br></a>
										<a href="https://outlook.office.com/owa/?realm=mavs.uta.edu/">MAV Mail</a></p></h1></p><p align="center">
										</p></div>
						</div>
						</form>
						<div id="tfheader">
    <form id="tfnewsearch" method="get" action="http://www.mywebsite.com">
        <p align="center"><b><input type="text" class="tftextinput" id="tftextinput" name="q" size="21" maxlength="120"><input type="submit" value="search" class="tfbutton"></b></p>
    </form>
<div class="tfclear"></div>
</div>

<script>
    var a = document.getElementById('tfnewsearch');
    a.addEventListener('submit',function(e) {
        e.preventDefault();
        var b = document.getElementById('tftextinput').value;
        window.location.href = 'http://localhost:8080/mymavappoint/'+b;

    });

</script>
					</div>
					<div class="panel-footer ">
					</div>
                </div>
			</div>
		</div>
	</div>


<%@ include file="templates/footer.jsp" %>