<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			<html>

			<head>
				<link rel="stylesheet" href="Frontend/css/cartTable.css">
				<style>
					html {
						font-size: 100%;
					}
				</style>
			</head>


			<body>
				<br>
				<br>
				<div class="container">
					<div class="row">
						<h2 style=""> QUẢN LÝ GIỎ HÀNG</h2> <br>
						<div class="col-10">
							<table class="table-cart-checkout mytable table-convert-price">
								<tr>
									<td>STT</td>
									<td>Ảnh</td>
									<td>Tên sản phẩm</td>
									<td>Đơn giá</td>
									<td>Số lượng</td>
									<td>Tổng</td>
									<td>Xóa</td>
								</tr>
								<c:forEach var="cartProduct" items="${cartProducts}" varStatus="loop">
									<tr class="cart_line" id="item${cartProduct.productId}">
										<div>
											<td>${loop.index+1}</td>
											<td><img src="/img/${cartProduct.productId}.png" style="width: 150px"
													class="cart-img"></td>
											<td style="text-align: center; mergin-top: -52px;">
												<p class="cart_ten"><a
														href="<%=request.getContextPath()%>/product=${cartProduct.productId}">${cartProduct.productName}</a>
												</p>
												<p class="cart_masanpham">Mã sản phẩm:
													<span>${cartProduct.productId}</span>
												</p>
												<p class="">Bảo hành : ${cartProduct.warranty} tháng</p>
											</td>
											<td class="covertPriceProduct">
												<fmt:formatNumber value="${cartProduct.productPrice}" type="number"
													pattern="#,##0" /> VNĐ
											</td>
											<td>
												<input class="input_num_cart" type="number"
													value="${cartProduct.quantity}" min="1"
													onchange="changeQuanity(${cartProduct.productId},this.value,${cartProduct.productPrice})">
											</td>
											<td><b>
													<span class="total" id="item${cartProduct.productId}_total"
														name="total_price">${cartProduct.productPrice*cartProduct.quantity}
													</span>
													VNĐ</b></td>
											<td class="cart-img">
												<a class="btn btn-danger"
													onclick="deleteFromCart(${cartProduct.productId})"><span
														class="glyphicon glyphicon-trash"></span></a>
											</td>
										</div>

									</tr>
								</c:forEach>
								<tr>
									<c:if test="${checkEmpty != 0 }">
										<td colspan="3">
											<a class="btn btn-primary"
												href="<%=request.getContextPath()%>/product"><span
													class="glyphicon glyphicon-hand-left"></span> Mua thêm sản phẩm
												khác</a>
											&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
											<a class="btn btn-warning "
												href="<%=request.getContextPath()%>/checkout"><span
													class="glyphicon glyphicon-check"></span> Thanh toán</a>
										</td>
									</c:if>

									<c:if test="${checkEmpty == 0 }">
										<td colspan="3">
											<a class="btn btn-primary" href="<%=request.getContextPath()%>/product">Mua
												thêm sản phẩm khác</a>
										</td>
									</c:if>


									<td colspan="4">
										<p class="cart_tongdonhang">Tổng giá trị đơn hàng : <span id="ordertotal">

											</span> VNĐ</p>
									</td>
								</tr>

							</table>

						</div>

						<div class="col-xs-1">

						</div>

					</div>
				</div>

				<script src="<c:url value='/js/client/cartAjax.js'/>"></script>

			</body>

			</html>