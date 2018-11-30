<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Deck App</title>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>

<style type="text/css">
.row {
	width: 100%
}

#answerText, #cardScoring {
	display: none;
}
</style>
</head>
<body class="bg-light">

	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="/deck-app/">Remember</a>
		<div class="collapse navbar-collapse" id="navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDecksDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Decks</a>
					<div class="dropdown-menu shadow-sm"
						aria-labelledby="navbarDecksDropdown">
						<c:forEach items="${categories}" var="category">
							<a class="dropdown-item" href="viewCategory?id=${category.key}">${category.value}</a>
						</c:forEach>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="addCategory">Add deck</a>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarCardsDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Cards</a>
					<div class="dropdown-menu shadow-sm"
						aria-labelledby="navbarCardsDropdown">
						<a class="dropdown-item" href="/deck-app/">View all cards</a> <a
							class="dropdown-item" href="addCard">Add card</a>
					</div></li>
			</ul>
		</div>
		<a class="nav-link ml-auto mx-3 btn btn-sm btn-primary text-white"
			href="studyDeck">Study</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbar" aria-controls="navbar" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

	</nav>

	<div class="container" style="margin-top: 6em;">