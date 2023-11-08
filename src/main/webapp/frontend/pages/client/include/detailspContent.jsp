<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

			<head>
				<link rel="stylesheet" href="Frontend/css/detailsp.css">
			</head>

			<body>
				<div class="container">
					<div class="card">
						<div class="container-fliud">
							<div class="wrapper row">
								<div class="preview col-md-6">

									<div class="preview-pic tab-content">
										<div class="tab-pane active" id="pic-1"><img style="width: 800px;"
												src="img/${product.id}.png" />
										</div>
									</div>
								</div>
								<div class="details col-md-6">
									<p style="display:none" id="spid">${product.id}</p>
									<h2 class="product-title">${product.name}</h2>
									<h4 class="price">Mô tả sản phẩm</h4>
									<p class="product-description"><i class="fa-solid fa-microchip"></i> CPU:
										${product.cpu.name}</p>
									<p class="product-description"><i class="fa-solid fa-memory"></i> RAM:
										${product.ram.size} GB</p>
									<p class="product-description"><i class="fa-solid fa-laptop"></i> Màn hình:
										${product.screen.panel}
										${product.screen.size} inch <c:if test="${product.screen.touch eq true}"> cảm
											ứng</c:if>
									</p>
									<p class="product-description"><i class="fa-solid fa-floppy-disk"></i> Bộ nhớ:
										${product.disk.type} ${product.disk.size} GB
									</p>
									<p class="product-description"><i class="fa-solid fa-battery-full"></i> Dung lượng
										pin: ${product.battery.capacity} mAh</p>
									<p class="product-description"><i class="fa-solid fa-gear"></i> Hệ điều hành:
										${product.os.name}</p>
									<p class="product-description"><span class="important">THƯƠNG HIỆU:</span>
										${product.brand.name}</p>
									<p class="product-description"><span class="important">THÔNG TIN CHUNG:</span>
										${product.description}</p>
									<p class="product-description"><span class="important">BẢO HÀNH:</span>
										${product.warranty} tháng</p>
									<h4 class="price" id="blabla">Giá bán: <span id="priceConvert">
											<fmt:formatNumber value="${product.price}" type="number" pattern="#,##0" />
											VNĐ
										</span></h4>
									<div class="action">
										<button class="add-to-cart btn btn-warning" type="button">
											<span class="glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ
											hàng</button>
									</div>
								</div>
							</div>							
						</div>
						
					</div>
					<div class="section group">
						<h4 style="font-weight: bold;font-size: 40px;">Sản phẩm tương tự</h4>
						<c:forEach items="${recommends}" var="recommend" varStatus="loop">
							<div class="grid_1_of_4 images_1_of_4 products-info">
								<a href="sp=${recommend.id}">
									<img style="width: 300px; height: 238px" src="img/${recommend.id}.png">
									<h3 style="font-weight: bold;">${recommend.name}</h3>
								</a>
								<div class="showcauhinh" style="font-size: 12px">
									<table style="margin: 6%;">
										<tr>
											<td style="width: 62%;text-align:left;">
												<span class="cpu"><i class="fa-solid fa-microchip"></i>
													${recommend.cpu.name}</span>
											</td>
											<td style="width: 36%; text-align: left;">
												<span class="screen"><i class="fa-solid fa-laptop"></i>
													${recommend.screen.panel} ${recommend.screen.size} inch
													<c:if test="${recommend.screen.touch eq true}"> cảm ứng
													</c:if>
												</span>
											</td>
										</tr>
										<tr>
											<td style="width: 64%;text-align:left;">
												<span class="os"><i class="fa-solid fa-gear"></i>
													${recommend.os.name}</span>
											</td>
											<td style="width: 38%;text-align:left;">
												<span class="ram"><i class="fa-solid fa-memory"></i>
													${recommend.ram.size}
													GB</span>
											</td>
										</tr>
									</table>
								</div>
								<h3>
									<fmt:formatNumber value="${recommend.price}" type="number"
										pattern="#,##0" /> VNĐ
								</h3>
								<button onClick="addToCart(${recommend.id})" class="btn btn-warning">
									<span class="glyphicon glyphicon-shopping-cart pull-center">
									</span> Giỏ hàng</button>
								<h3></h3>
							</div>
						</c:forEach>
					</div><br>
				</div>
			</body>

			<script src="<c:url value='/js/client/detailspAjax.js'/>"></script>