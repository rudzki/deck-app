<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="card-columns">

	<c:forEach items="${cards}" var="card">

		<div class="card mb-4 shadow-sm">
			<div class="card-body">
				<h5 class="card-title">${card.question}</h5>
				<div class="d-flex justify-content-between align-items-center">
					<div class="btn-group">
						<a class="btn btn-outline-primary btn-sm"
							href="card/${card.id}" role="button">View</a>
					</div>
					<small class="text-muted"><a href="deck/${card.categoryId}/">${card.categoryName}</a></small>
				</div>
			</div>
		</div>

	</c:forEach>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />

