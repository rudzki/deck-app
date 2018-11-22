<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h3>Cards in ${categoryName}</h3>

<div class="cards">
	<c:forEach items="${cards}" var="card">
		<div class="card">
			<h6 class="card-title">${card.question}</h6>
			<a href="viewCard?id=${card.id}">Details &raquo;</a>
		</div>
	</c:forEach>
</div>

<h4>
	<a href="addCard">Add a card</a>
</h4>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />