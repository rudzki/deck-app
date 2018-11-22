<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h1 class="bd-title" id="content">All Cards</h1>
<div class="card-deck">
          <div class="row">
          
	<c:forEach items="${cards}" var="card">
	
	<div class="col-md-4">
              <div class="card mb-4 shadow-sm">
                <div class="card-body">
                  <h6 class="card-title">${card.question}</h6>
                  <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                    	<a class="btn btn-outline-primary btn-sm" href="viewCard?id=${card.id}" role="button">View</a>
                    </div>
                    <small class="text-muted">${categoryName}</small>
                  </div>
                </div>
              </div>
            </div>
		
	</c:forEach>
	</div>
</div>

<h4>
	<a href="addCard">Add a card</a>
</h4>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />

