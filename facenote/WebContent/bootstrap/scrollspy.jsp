<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"></script>

</head>
<body>
	<div class="container">
		<h2>스크롤스파이</h2>
		<nav id="navbar-example" class="navbar navbar-default navbar-static"
			role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button class="navbar-toggle collapsed" type="button"
						data-toggle="collapse" data-target=".navbar-scrollspy">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Project Name</a>
				</div>
				<div class="collapse navbar-collapse navbar-scrollspy">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#fat">@fat</a></li>
						<li class=""><a href="#mdo">@mdo</a></li>
						<li class="dropdown"><a href="#" id="navbarDrop1"
							class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false">Dropdown <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="navbarDrop1">
								<li class=""><a href="#one" tabindex="-1">one</a></li>
								<li class=""><a href="#two" tabindex="-1">two</a></li>
								<li class="divider"></li>
								<li class=""><a href="#three" tabindex="-1">three</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</nav>
		<div data-spy="scroll" data-target="#navbar-example" data-offset="0"
			class="scrollspy-example">
			<h4 id="fat">@fat</h4>
			<p>Ad leggings keytar, brunch id art party dolor labore.
				Pitchfork yr enim lo-fi before they sold out qui. Tumblr
				farm-to-table bicycle rights whatever. Anim keffiyeh carles
				cardigan. Velit seitan mcsweeney's photo booth 3 wolf moon irure.
				Cosby sweater lomo jean shorts, williamsburg hoodie minim qui you
				probably haven't heard of them et cardigan trust fund culpa
				biodiesel wes anderson aesthetic. Nihil tattooed accusamus, cred
				irony biodiesel keffiyeh artisan ullamco consequat.</p>
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<h4 id="mdo">@mdo</h4>
			<p>Veniam marfa mustache skateboard, adipisicing fugiat velit
				pitchfork beard. Freegan beard aliqua cupidatat mcsweeney's vero.
				Cupidatat four loko nisi, ea helvetica nulla carles. Tattooed cosby
				sweater food truck, mcsweeney's quis non freegan vinyl. Lo-fi wes
				anderson +1 sartorial. Carles non aesthetic exercitation quis
				gentrify. Brooklyn adipisicing craft beer vice keytar deserunt.</p>
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<h4 id="one">one</h4>
			<p>Occaecat commodo aliqua delectus. Fap craft beer deserunt
				skateboard ea. Lomo bicycle rights adipisicing banh mi, velit ea
				sunt next level locavore single-origin coffee in magna veniam. High
				life id vinyl, echo park consequat quis aliquip banh mi pitchfork.
				Vero VHS est adipisicing. Consectetur nisi DIY minim messenger bag.
				Cred ex in, sustainable delectus consectetur fanny pack iphone.</p>
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<h4 id="two">two</h4>
			<p>In incididunt echo park, officia deserunt mcsweeney's proident
				master cleanse thundercats sapiente veniam. Excepteur VHS elit,
				proident shoreditch +1 biodiesel laborum craft beer. Single-origin
				coffee wayfarers irure four loko, cupidatat terry richardson master
				cleanse. Assumenda you probably haven't heard of them art party
				fanny pack, tattooed nulla cardigan tempor ad. Proident wolf
				nesciunt sartorial keffiyeh eu banh mi sustainable. Elit wolf
				voluptate, lo-fi ea portland before they sold out four loko.
				Locavore enim nostrud mlkshk brooklyn nesciunt.</p>
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<h4 id="three">three</h4>
			<p>Ad leggings keytar, brunch id art party dolor labore.
				Pitchfork yr enim lo-fi before they sold out qui. Tumblr
				farm-to-table bicycle rights whatever. Anim keffiyeh carles
				cardigan. Velit seitan mcsweeney's photo booth 3 wolf moon irure.
				Cosby sweater lomo jean shorts, williamsburg hoodie minim qui you
				probably haven't heard of them et cardigan trust fund culpa
				biodiesel wes anderson aesthetic. Nihil tattooed accusamus, cred
				irony biodiesel keffiyeh artisan ullamco consequat.</p>
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<p>Keytar twee blog, culpa messenger bag marfa whatever delectus
				food truck. Sapiente synth id assumenda. Locavore sed helvetica
				cliche irony, thundercats you probably haven't heard of them
				consequat hoodie gluten-free lo-fi fap aliquip. Labore elit placeat
				before they sold out, terry richardson proident brunch nesciunt quis
				cosby sweater pariatur keffiyeh ut helvetica artisan. Cardigan craft
				beer seitan readymade velit. VHS chambray laboris tempor veniam.
				Anim mollit minim commodo ullamco thundercats.</p>
				<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		</div>
	</div>

</body>
</html>