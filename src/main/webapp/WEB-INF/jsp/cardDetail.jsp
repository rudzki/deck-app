<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="col-md-8 mx-auto">
	<div class="card mb-4 shadow-sm">
		<div class="card-body">
			<h5 class="card-title">${card.question}</h5>
			<p class="card-text">${card.answer}</p>
			<div class="progress mb-3" style="height: 10px; border-radius: 0;">
				<c:set value="${100 * averageScore / 2}" var="score" />
				<c:if test="${score > 0}">
					<c:set value="bg-danger" var="barColor" />
				</c:if>
				<c:if test="${score > 49}">
					<c:set value="bg-warning" var="barColor" />
				</c:if>
				<c:if test="${score > 79}">
					<c:set value="bg-success" var="barColor" />
				</c:if>


				<div class="progress-bar ${barColor}" role="progressbar"
					aria-valuenow="${score}" style="width: ${score}%;"
					aria-valuemin="0" aria-valuemax="100"></div>
			</div>
			<div class="d-flex justify-content-between align-items-center">
				<small class="text-muted"> Added on <fmt:parseDate
						value="${card.dateSubmitted}" pattern="y-M-dd'T'H:m"
						var="parsedDate" /> <fmt:formatDate type="date"
						value="${parsedDate}" />
				</small> <small class="text-muted">${card.categoryName}</small>
			</div>
		</div>
	</div>
	<div class="d-flex justify-content-end">
	<small><a href="deleteCard/${card.id}" class="text-danger">Delete</a></small>
	</div>
</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />