<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="col-md-4">
	<div class="card mb-4 shadow-sm">
		<div class="card-body">
			<p class="card-text">${card.question}</p>
			<p class="card-text">${card.answer}</p>
			<p class="card-text">${averageScore}</p>
			<p class="card-text">${card.dateSubmitted}</p>
			<div class="d-flex justify-content-between align-items-center">
				<div class="btn-group">
					<a class="btn btn-outline-primary btn-sm"
						href="viewCard?id=${card.id}" role="button">View</a>
				</div>
				<small class="text-muted">${categoryName}</small>
			</div>
		</div>
	</div>
</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />