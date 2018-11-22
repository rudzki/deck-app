<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="col-md-8 mx-auto">
	<div class="card mb-4 shadow-sm">
		<div class="card-body">
			<h6 class="card-title">${card.question}</h6>
			<div class="collapse" id="collapseAnswer">
				<p class="card-text mb-3">${card.answer}</p>
			</div>
<%-- 			<p class="card-text">${averageScore}</p>
			<p class="card-text">${card.dateSubmitted}</p> --%>
			<div class="d-flex justify-content-between align-items-center">
				<div class="btn-group">

					<button class="btn btn-primary btn-sm" type="button"
						data-toggle="collapse" data-target="#collapseAnswer"
						aria-expanded="false" aria-controls="collapseAnswer">
						Show Answer</button>
				</div>
				<small class="text-muted">${categoryName}</small>
			</div>
		</div>
	</div>
</div>
<div class="col-md-8 mx-auto">
	<form action="scoreCard" method="POST">
		<div class="form-group">
			<label for="score">How did you do?</label> <select name="score"
				id="score" class="form-control">
				<option value="3">Correct and quick</option>
				<option value="2">Correct but slow</option>
				<option value="1">Incorrect</option>
			</select>
		</div>
		<input type="hidden" name="cardId" value="${card.id}" />
		<button type="submit" class="btn btn-primary">Next</button>

	</form>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />