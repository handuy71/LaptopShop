<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<link rel="stylesheet" href="Frontend/css/searchResult.css">
			<script src="<c:url value='/js/client/homeAjax.js'/>"></script>
			<!--start-image-slider---->
			<div class="wrap">
				<div class="image-slider">
					<!-- Slideshow 1 -->
					<ul class="rslides" id="slider1">
						<li><img src="Frontend/img/laptop1.jpg" alt=""></li>
						<li><img src="Frontend/img/laptop2.png" alt=""></li>
						<li><img src="Frontend/img/laptop3.png" alt=""></li>
					</ul>
					<!-- Slideshow 2 -->
				</div>
				<!--End-image-slider---->
			</div>
			<div class="clear"> </div>
			<div class="wrap">
				<div class="content">
					<div class="top-3-grids">
						<div class="section group">
							<div class="grid_1_of_3 images_1_of_3">
								<a href="single.html"><img src="Frontend/img/acer.jpg"></a>
								<h3>Thương hiệu nổi bật </h3>
							</div>
							<div class="grid_1_of_3 images_1_of_3 ">
								<a href="single.html"><img src="Frontend/img/lenovo2.png"
										style="background-color: white"></a>
								<h3>Thương hiệu nổi bật</h3>
							</div>
							<div class="grid_1_of_3 images_1_of_3 ">
								<a href="single.html"><img src="Frontend/img/dell.png"
										style="background-color: white"></a>
								<h3>Thương hiệu nổi bật</h3>
							</div>
						</div>
					</div>

					<div class="content-grids">
						<c:choose>
							<c:when
								test="${empty param.name && empty param.CPUId && empty param.RAMId && empty param.BrandId && empty param.ScreenId && empty param.DiskId && empty param.OSId && empty param.BatteryId && empty param.minPrice && empty param.maxPrice}">
								<h4>DANH SÁCH LAPTOP MỚI NHẤT</h4>
							</c:when>
							<c:otherwise>
								<h4>Kết quả tìm kiếm (${products.totalElements} sản phẩm)</h4>
							</c:otherwise>
						</c:choose>

						<div class="section group">
							<c:forEach items="${products.content}" var="product" varStatus="loop">
								<c:if test="${loop.index % 4 == 0 && not loop.first}">
						</div><br>
						<div class="section group">
							</c:if>
							<div class="grid_1_of_4 images_1_of_4 products-info">
								<a href="sp=${product.id}">
									<img style="width: 300px; height: 238px" src="img/${product.id}.png">
									<h3 style="font-weight: bold;">${product.name}</h3>
								</a>
								<div class="showcauhinh" style="font-size: 12px">
									<table style="margin: 6%;">
										<tr>
											<td style="width: 62%;text-align:left;">
												<span class="cpu"><i class="fa-solid fa-microchip"></i>
													${product.cpu.name}</span>
											</td>
											<td style="width: 36%; text-align: left;">
												<span class="screen"><i class="fa-solid fa-laptop"></i>
													${product.screen.panel} ${product.screen.size} inch
													<c:if test="${product.screen.touch eq true}"> cảm ứng</c:if>
												</span>
											</td>
										</tr>
										<tr>
											<td style="width: 64%;text-align:left;">
												<span class="os"><i class="fa-solid fa-gear"></i>
													${product.os.name}</span>
											</td>
											<td style="width: 38%;text-align:left;">
												<span class="ram"><i class="fa-solid fa-memory"></i> ${product.ram.size}
													GB</span>
											</td>
										</tr>
									</table>
								</div>
								<h3>
									<fmt:formatNumber value="${product.price}" type="number" pattern="#,##0" /> VNĐ
								</h3>
								<button onClick="addToCart(${product.id})" class="btn btn-warning">
									<span class="glyphicon glyphicon-shopping-cart pull-center">
									</span> Giỏ hàng</button>
								<h3></h3>
							</div>
							<c:if test="${loop.last}">
						</div>
						</c:if>
						</c:forEach>
						<c:if test="${products.totalPages !=0}">
							<div class="paging section group">
								<c:if test="${products.pageable.pageNumber != 0}">
									<a href="product?page=${products.pageable.pageNumber}">Back</a>
								</c:if>
								<c:if test="${products.pageable.pageNumber == 0}">
									<a class="current">1</a>
								</c:if>
								<c:if test="${products.pageable.pageNumber != 0}">
									<a href="product?page=1">1</a>
								</c:if>

								<c:forEach var="pag" begin="2" end="${products.totalPages}" step="1">
									<c:if test="${products.pageable.pageNumber == pag-1}">
										<a class="current">${pag}</a>
									</c:if>
									<c:if test="${products.pageable.pageNumber != pag-1}">
										<a href="product?page=${pag}">${pag}</a>
									</c:if>
								</c:forEach>

								<c:if test="${products.pageable.pageNumber != products.totalPages-1}">
									<a href="product?page=${products.pageable.pageNumber+2}">Next</a>
								</c:if>
							</div><br>
						</c:if>
					</div>

				</div>



			</div>
			<div class="content-sidebar" style="float: left;">
				<h4>Tìm kiếm sản phẩm</h4>
				<form>
					<input class="form-control" type="text" name="name" value="${name}">
					<p>Mức giá</p>
					<div class="select-range">
						<input oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1')"
							type="text" class="form-control" style="width:47%;display: inline-flex;" name="minPrice"
							id="priceMin" placeholder="Giá thấp nhất">
						<span> - </span>
						<input oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1')"
							type="text" class="form-control" style="width:47%;display: inline-flex;" name="maxPrice"
							id="priceMax" placeholder="Giá cao nhất">
					</div>
					<p>Thương hiệu</p>
					<select name="BrandId" class="form-control">
						<option value="">Tất cả nhà sản xuất</option>
						<c:forEach items="${brands}" var="brand" varStatus="loop">
							<option value="${brand.id}">${brand.name}</option>
						</c:forEach>
					</select>
					<p>CPU</p>
					<select name="CPUId" class="form-control">
						<option value="">Tất cả CPU</option>
						<c:forEach items="${cpus}" var="cpu" varStatus="loop">
							<option value="${cpu.id}">${cpu.name}</option>
						</c:forEach>
					</select>
					<p>RAM</p>
					<select name="RAMId" class="form-control">
						<option value="">Tất cả ram</option>
						<c:forEach items="${rams}" var="ram" varStatus="loop">
							<option value="${ram.id}">${ram.size} GB</option>
						</c:forEach>
					</select>
					<p>Màn hình</p>
					<select name="ScreenId" class="form-control">
						<option value="">Tất cả màn hình</option>
						<c:forEach items="${screens}" var="screen" varStatus="loop">
							<option value="${screen.id}">${screen.panel} ${screen.size} inch
								<c:if test="${screen.touch eq true}"> cảm ứng</c:if>
							</option>
						</c:forEach>
					</select>
					<p>Bộ nhớ</p>
					<select name="DiskId" class="form-control">
						<option value="">Tất cả bộ nhớ</option>
						<c:forEach items="${disks}" var="disk" varStatus="loop">
							<option value="${disk.id}">${disk.type} ${disk.size} GB</option>
						</c:forEach>
					</select>
					<p>Dung lượng pin</p>
					<select name="BatteryId" class="form-control">
						<option value="">Tất cả dung lượng pin</option>
						<c:forEach items="${batteries}" var="battery" varStatus="loop">
							<option value="${battery.id}">${battery.capacity} mAh</option>
						</c:forEach>
					</select>
					<p>Hệ điều hành</p>
					<select name="OSId" class="form-control">
						<option value="">Tất cả hệ điều hành</option>
						<c:forEach items="${oss}" var="os" varStatus="loop">
							<option value="${os.id}">${os.name}</option>
						</c:forEach>
					</select>
					<input class="search-submit" type="submit" value="Lọc sản phẩm">
				</form>
			</div>

			</div>
			<div class="clear"> </div>

			</div>

			<script>
				// Function to get URL query parameters
				function getQueryParam(param) {
					const urlParams = new URLSearchParams(window.location.search);
					return urlParams.get(param);
				}

				// Function to pre-fill form fields
				function prefillForm() {
					// Get values from URL query parameters
					const name = getQueryParam("name");
					const minPrice = getQueryParam("minPrice");
					const maxPrice = getQueryParam("maxPrice");
					const brandId = getQueryParam("BrandId");
					const cpuId = getQueryParam("CPUId");
					const ramId = getQueryParam("RAMId");
					const screenId = getQueryParam("ScreenId");
					const diskId = getQueryParam("DiskId");
					const batteryId = getQueryParam("BatteryId");
					const osId = getQueryParam("OSId");

					// Pre-fill or pre-select form fields
					document.querySelector('input[name="name"]').value = name || '';
					document.querySelector('input[name="minPrice"]').value = minPrice || '';
					document.querySelector('input[name="maxPrice"]').value = maxPrice || '';
					document.querySelector('select[name="BrandId"]').value = brandId || '';
					document.querySelector('select[name="CPUId"]').value = cpuId || '';
					document.querySelector('select[name="RAMId"]').value = ramId || '';
					document.querySelector('select[name="ScreenId"]').value = screenId || '';
					document.querySelector('select[name="DiskId"]').value = diskId || '';
					document.querySelector('select[name="BatteryId"]').value = batteryId || '';
					document.querySelector('select[name="OSId"]').value = osId || '';
				}

				// Call the prefillForm function when the page loads
				window.onload = prefillForm;
			</script>