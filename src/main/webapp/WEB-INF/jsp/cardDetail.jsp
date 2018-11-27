<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="col-md-8 mx-auto">
	<div class="card mb-4 shadow-sm">
		<div class="card-body">
			<h5 class="card-title">${card.question}</h5>
			<p class="card-text">${card.answer}</p>
 			<p class="card-text">${averageScore}</p>
			<p class="card-text">${card.dateSubmitted}</p>
			<div class="d-flex justify-content-between align-items-center">

				<small class="text-muted">${card.categoryName}</small>
			</div>
		</div>
	</div>
</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />