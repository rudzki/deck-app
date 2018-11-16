<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h3>All Cards</h3>

<div class="cards">
	<c:forEach items="${cards}" var="card">
		<div class="card">
			<p class="cardQuestion">${card.question}</p>
			<a href="viewCard?id=${card.id}">Details &raquo;</a>
		</div>
	</c:forEach>
</div>

<h4>
	<a href="addCard">Add your card</a>
</h4>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />