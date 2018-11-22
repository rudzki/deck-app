<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="row">
<div class="col-md-4">

<form action="publishCard" method="POST">
	<div class="form-group">
		<label for="question">Question</label> <input type="text"
			name="question" id="question" class="form-control" />
	</div>
	<div class="form-group">
		<label for="answer">Answer</label>
		<textarea name="answer" id="answer" class="form-control" rows="10"
			cols="80"></textarea>
	</div>
	<div class="form-group">
		<label for="category">Category</label> <select name="categoryId"
			id="categoryId" class="form-control">
			
			<c:forEach items="${categories}" var="category">
			<option value="${category.key}">${category.value}</option>
			</c:forEach>
		</select>
	</div>
 	<button type="submit" class="btn btn-primary">Add Card</button>
</form>
                </div>
              </div>
		
<c:import url="/WEB-INF/jsp/common/footer.jsp" />