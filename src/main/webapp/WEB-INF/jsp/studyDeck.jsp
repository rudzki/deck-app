<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="col-md-8 mx-auto">


	<div class="card mb-4 shadow-sm">
		<div class="card-body">
			<h5 class="card-title">${card.question}</h5>
			<div id="answerText">
				<p class="card-text mb-3">${card.answer}</p>
			</div>
			<%-- 			<p class="card-text">${averageScore}</p>
			<p class="card-text">${card.dateSubmitted}</p> --%>
			<div class="d-flex justify-content-between align-items-center">
				<button class="btn btn-outline-primary btn-sm" type="button"
					id="answerButton">Show Answer</button>
				<div id="cardScoring">
					<form class="form-inline" action="scoreCard" method="POST">
						<div class="form-row">
							<div class="col-auto">
								<select name="score" id="score"
									class="form-control form-control-sm">
									<option value="2">Answered correctly and quickly</option>
									<option value="1">Answered correctly but slowly</option>
									<option value="0">Answered incorrectly</option>
								</select> <input type="hidden" name="cardId" value="${card.id}" />
							</div>
							<div class="col-auto">
								<button type="submit" class="btn btn-primary btn-sm">Next</button>
							</div>
						</div>
					</form>
				</div>
				<small class="text-muted">${card.categoryName}</small>
			</div>
		</div>
	</div>


	<div class="card">
		<div class="card-header text-sm text-center text-muted bg-light">
			<small>Up Next</small>
		</div>
		<ul class="list-group list-group-flush">
			<c:forEach items="${sortedCards}" var="nextCard">
				<li class="list-group-item bg-light">
					<span class="d-flex justify-content-between align-items-center">
						<small class="text-muted">Card ID: ${nextCard.key}</small>
						<small class="text-muted">Score: ${nextCard.value}</small>
					</span>
				</li>
			</c:forEach>
		</ul>
	</div>




</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />