<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h3>Card Detail</h3>

<div class="cards">
	<div class="card">
		<p class="question">${card.question}</p>
		<p class="answer">${card.answer}</p>
		<p class="categoryName">Category ID: ${card.categoryId}</p>
		<p class="averageScore">Average Score: ${averageScore}</p>
		<p class="dateAdded">Date Added: ${card.dateSubmitted}</p>
	</div>

	<form action="advanceDeck" method="POST" class="form-inline">
		<div class="form-group">
			<label for="category">How did you do?</label> <select name="categoryId"
				id="categoryId" class="form-control">
				<option value="3">Correct and quick</option>
				<option value="2">Correct but slow</option>
				<option value="1">Incorrect</option>
			</select>
		</div>
		<div class="form-group">
			<label>&nbsp;</label> <input type="submit" name="submit"
				value="Add Card" class="btn btn-default" />
		</div>
	</form>

</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />