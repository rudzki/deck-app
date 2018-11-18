<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<form action="addCategory" method="POST" class="form-inline">
	<div class="form-group">
		<label for="newCategoryName">New Category Name</label> <input type="text"
			name="newCategoryName" id="newCategoryName" class="form-control" />
	</div>
	<div class="form-group">
		<label>&nbsp;</label> <input type="submit" name="submit"
			value="Create Category" class="btn btn-default" />
	</div>
</form>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />