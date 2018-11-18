<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h3>Card Detail</h3>

<div class="cards">
	<div class="card">
		<p class="question">${card.question}</p>
		<p class="answer">${card.answer}</p>
		<p class="categoryName">Category: ${categoryName}</p>
		<p class="averageScore">Average Score: ${averageScore}</p>
		<p class="dateAdded">Date Added: ${card.dateSubmitted}</p>				
	</div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />