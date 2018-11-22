<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h1 class="display-4 mb-3">Cards in ${categoryName}</h1>

<div class="card-columns">
	<c:forEach items="${cards}" var="card">

		<div class="card mb-4 shadow-sm">
			<div class="card-body">
				<h6 class="card-title">${card.question}</h6>
				<div class="d-flex justify-content-between align-items-center">
					<div class="btn-group">
						<a class="btn btn-outline-primary btn-sm"
							href="viewCard?id=${card.id}" role="button">View</a>
					</div>
					<small class="text-muted">${categoryName}</small>
				</div>
			</div>
		</div>

	</c:forEach>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />