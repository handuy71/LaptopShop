<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			<html>

			<head>

				<title>Đơn hàng</title>
			</head>

			<body>

				<div class="container">
					<form method="POST" action="<%=request.getContextPath()%>/thankyou">
						<div class="row">
							<br><br>
							<c:if test="${pageContext.request.userPrincipal.name != null}">
								<div class="col-md-3">
									<br>
									<p class="border-p" style="line-height:1.5;"><b>Thông tin khách hàng</b></p>

									<p style="line-height:2;">Họ tên Quý khách </p>
									<input size="27" value="${name}" disabled>

									<p style="line-height:2;">Địa chỉ Email </p>
									<input size="27" value="${email}" disabled>

									<p style="line-height:2;"> Số điện thoại </p>
									<input size="27" value="${phone}" disabled>

									<p style="line-height:2;">Địa chỉ(số nhà, đường, tỉnh thành) </p>
									<textarea rows="5" cols="29" disabled>${address}</textarea>

									<br><br>
								</div>
							</c:if>
							<div class="col-md-3">
								<br>
								<p class="border-p" style="line-height:1.5;"><b>Thông tin nhận hàng</b></p>

								<p style="line-height:2;">Họ tên người nhận hàng *</p>
								<input size="27" name="name" value="${name}" required>

								<p style="line-height:2;">Số điện thoại *</p>
								<input size="27" name="phone" value="${phone}" required>


								<p style="line-height:2;">Địa chỉ(số nhà, đường, tỉnh thành) *</p>
								<textarea rows="5" cols="29" name="address" required>${address}</textarea>

								<br><br>
								<!-- <input type="hidden" id="tongGiaTri" name="tongGiaTri"> -->
							</div>

							<div class="col-md-6">
								<br>
								<p class="border-p" style="line-height:1.5;"><b>Giỏ hàng</b></p>
								<br>

								<table class="table-cart-checkout mytable">
									<tr>
										<th>Ảnh</th>
										<th>Tên sản phẩm</th>
										<th>Đơn giá</th>
										<th>Tổng</th>
									</tr>
									<c:forEach items="${cartProducts}" var="cartProduct">

										<tr style="text-align: center;">
											<td>
												<img src="/img/${cartProduct.productId}.png" alt="not found img"
													class="img-reponsive fix-size-img">
											</td>
											<td style="color:green">
												${cartProduct.productName}
											</td>

											<td class="donGia">
												<div class="check " style="display: inline-block; ">
													<fmt:formatNumber value="${cartProduct.productPrice}" type="number"
														pattern="###0" /> VNĐ
												</div>
												<div style="display: inline-block; "> x ${cartProduct.quantity}</div>
											</td>

											<td>
												<div class="total">
													<fmt:formatNumber
														value="${cartProduct.productPrice*cartProduct.quantity}"
														type="number" pattern="###0" /> VNĐ
												</div>
											</td>
										</tr>
										<c:forEach items="${cartProduct.gifts}" var="gift">

											<tr style="text-align: center;">
												<td></td>
												<td style="color:rgb(0, 81, 128)">
													${gift}
												</td>

												<td class="donGia">
													<div class="check " style="display: inline-block; ">0 VNĐ</div>
												</td>

												<td>
													<div class="total">0 VNĐ</div>
												</td>
											</tr>
										</c:forEach>
									</c:forEach>
								</table>


								<br>
								<p>Tổng giá trị đơn hàng: <b id="ordertotal"> </b></p>
								<br>
								&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
								<a href="<%=request.getContextPath()%>/cart" class="btn btn-primary">Quay lại giỏ
									hàng</a>
								&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;

								<button class="btn btn-danger pull-center" type="submit" id="submit">Gửi đơn
									hàng</button>

								<br><br>

							</div>

						</div>
					</form>
				</div>
				<script src="<c:url value='/js/client/checkoutAjax.js'/>"></script>
				<!-- <script src="<c:url value='/js/client/cartAjax.js'/>" ></script>	 -->
			</body>

			</html>