<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/cart.css">
<title>カート画面</title>
<script src="//code.jquery.com/jquery-1.12.1.min.js"></script>

<script type="text/javascript">

		function deleteCheck(){
			var check_count = $('.product :checked').length;
			if(check_count > 0){
				$("#deleteButton").removeAttr("disabled");
			}else{
				$("#deleteButton").attr("disabled","disabled");
			}
	}

</script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div id="contents">
	<h1>カート画面</h1>
	<!-- カートに商品が入っている場合 -->
 	<s:if test="cartInfoDTOList.size() > 0">
		<s:form action="DeleteCartAction" name = "form">
			<div id="cart">

					<table class="product">
					<thead>
							<tr>
								<th>削除</th>
								<th>商品名</th>
								<th>ふりがな</th>
								<th>商品画像</th>
								<th>値段</th>
								<th>発売会社名</th>
								<th>発売年月日</th>
								<th>購入個数</th>
								<th>合計金額</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="cartInfoDTOList">
							<tr>
							<td>
								<input type="checkbox" value="<s:property value='productId'/>" name="productIdArray" class="deleteCheck" onclick="deleteCheck()">
							</td>
							<td><s:property value="productName"/></td><td><s:property value="productNameKana"/></td>
							<td><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' alt="product" /></td>
							<td><s:property value="price"/>円</td>
							<td><s:property value="releaseCompany"/></td>
							<td><s:property value="releaseDate"/></td>
							<td><s:property value="productCount"/>個</td>
							<td><s:property value="subtotal"/>円</td>
							</tr>
							</s:iterator>
						</tbody>
				</table>

			</div>

		<!-- カート合計金額 -->
		<div class="totalPrice">
			カート合計金額:
			<s:property value="totalPrice"/>円
		</div>

		<!-- 決済ボタン -->
		<div class="submit">
			<input type="button" value="決済" onclick="location.href='SettlementConfirmAction.action'" class="submit_btn"/>
		</div>
		<!-- 削除ボタン -->
			<div class="submit" id="delete">
				<input type="submit" value="削除" id="deleteButton" disabled = "disabled" class="submit_btn"/>
			</div>
		</s:form>

 	</s:if>
	<!-- カートに商品が入っていない場合 -->
	<s:else>
		<div class="info">カート情報はありません。</div>
	</s:else>

	<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>
