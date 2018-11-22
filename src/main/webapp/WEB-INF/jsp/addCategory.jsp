<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="row">
<div class="col-md-4">

<form action="addCategory" method="POST">
	<div class="form-group">
		<label for="newCategoryName">New Category Name</label> <input type="text"
			name="newCategoryName" id="newCategoryName" class="form-control" />
	</div>

		 	<button type="submit" class="btn btn-primary">Create Category</button>

</form>

</div></div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />