
//function addToCart(id)
//	{
//		$.ajax({
//			type: "GET",
//			url: "http://localhost:8080/laptopshop/api/gio-hang/addSanPham?id="+id,
//			success: function(result){
//				if(result.status == "false")
//				{
//					window.alert("Sản phẩm đang hết hàng, quý khách vui lòng quay lại sau");
//				}else
//				{
//					window.alert("Đã thêm sản phẩm vào giỏ hàng");
//				}
//			},
//			error : function(e){
//				alert("Error: ",e);
//				console.log("Error" , e );
//			}
//		});
//
//
//	}


$(document).ready(function(){
	ajaxGet(0);

	function ajaxGet(page){
		$.ajax({
			type: "GET",
			data: data,
            contentType : "application/json",
			url: "http://localhost:8080/api/product/"+ '?page=' + page,
			success: function(result){
				var content;
				var section = '<div class="section group">';
				var endsection = '</div>'+'<br>';
				$.each(result.content, function(i, sanPham){
					if(sanpham.screen.touch) var touch = 'cảm ứng';
					else var touch='';
					if(i != result.length-1)
					{
						if(i%4==0)
						{
							content = '';
							content = '<div class="grid_1_of_4 images_1_of_4 products-info"><a href="sp?id='+sanpham.id+'">' +
									'<img style="width: 300px; height: 238px" src="/laptopshop/img/'+sanpham.id+'.png">' +
									'<h3 style="font-weight: bold;">'+sanpham.name+'</h3></a>' +
									'<div class="showcauhinh" style="font-size: 12px"><table style="margin: 6%;"><tr>'+
									'<td style="width: 62%;text-align:left;"><span class="cpu"><i class="fa-solid fa-microchip"></i> 'sanpham.cpu.name'</span></td>'+
                                    '<td style="width: 36%;text-align:left;"><span class="screen"><i class="fa-solid fa-laptop"></i> 'sanpham.screen.panel+' '+sanpham.screen.size+' inch '+touch'</span></td>'+
                                    '</tr><tr>'+
                                    '<td style="width: 64%;text-align:left;"><span class="os"><i class="fa-solid fa-gear"></i> 'sanpham.os.name'</span></td>'+
                                    '<td style="width: 38%;text-align:left;"><span class="ram"><i class="fa-solid fa-memory"></i> 'sanpham.ram.size' GB</span></td>'+
                                    '</tr></table></div>'+
									'<h3>'+accounting.formatMoney(sanpham.price)+' VND</h3>'+
									'<button onClick="addToCart('+sanpham.id+')" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>'+
									'<h3></h3>'+
									'</div>';
						}else
						{
							content = content+'<div class="grid_1_of_4 images_1_of_4 products-info"><a href="sp?id='+sanpham.id+'">' +
									'<img style="width: 300px; height: 238px" src="/laptopshop/img/'+sanpham.id+'.png">' +
									'<h3 style="font-weight: bold;">'+sanpham.name+'</h3><a/>' +
									'<div class="showcauhinh" style="font-size: 12px"><table style="margin: 6%;"><tr>'+
                                    									'<td style="width: 62%;text-align:left;"><span class="cpu"><i class="fa-solid fa-microchip"></i> 'sanpham.cpu.name'</span></td>'+
                                                                        '<td style="width: 36%;text-align:left;"><span class="screen"><i class="fa-solid fa-laptop"></i> 'sanpham.screen.panel+' '+sanpham.screen.size+' inch '+touch'</span></td>'+
                                                                        '</tr><tr>'+
                                                                        '<td style="width: 64%;text-align:left;"><span class="os"><i class="fa-solid fa-gear"></i> 'sanpham.os.name'</span></td>'+
                                                                        '<td style="width: 38%;text-align:left;"><span class="ram"><i class="fa-solid fa-memory"></i> 'sanpham.ram.size' GB</span></td>'+
                                                                        '</tr></table></div>'+
									'<h3>'+accounting.formatMoney(sanpham.price)+' VND</h3>'+
									'<button onClick="addToCart('+sanpham.id+')" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>'+
									'<h3></h3>'+
									'</div>';
							if(i%4==3)
							{
								content = section + content + endsection;
								$('.content-grids').append(content);
							}
						}
					}else
					{
						if(i%4==0)
						{
							content = '';
							content = '<div class="grid_1_of_4 images_1_of_4 products-info"><a href="sp?id='+sanpham.id+'">' +
									'<img style="width: 300px; height: 238px" src="/laptopshop/img/'+sanpham.id+'.png">' +
									'<h3 style="font-weight: bold;">'+sanpham.name+'</h3>></a>' +
									'<h3>'+accounting.formatMoney(sanpham.price)+' VND</h3>'+
									'<div class="showcauhinh" style="font-size: 12px"><table style="margin: 6%;"><tr>'+
                                    									'<td style="width: 62%;text-align:left;"><span class="cpu"><i class="fa-solid fa-microchip"></i> 'sanpham.cpu.name'</span></td>'+
                                                                        '<td style="width: 36%;text-align:left;"><span class="screen"><i class="fa-solid fa-laptop"></i> 'sanpham.screen.panel+' '+sanpham.screen.size+' inch '+touch'</span></td>'+
                                                                        '</tr><tr>'+
                                                                        '<td style="width: 64%;text-align:left;"><span class="os"><i class="fa-solid fa-gear"></i> 'sanpham.os.name'</span></td>'+
                                                                        '<td style="width: 38%;text-align:left;"><span class="ram"><i class="fa-solid fa-memory"></i> 'sanpham.ram.size' GB</span></td>'+
                                                                        '</tr></table></div>'+
									'<button onClick="addToCart('+sanpham.id+')" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>'+
									'<h3></h3>'+
									'</div>';
							content = section + content + endsection;
							$('.content-grids').append(content);
						}else
						{
							content = content+'<div class="grid_1_of_4 images_1_of_4 products-info"><a href="sp?id='+sanpham.id+'">' +
									'<img style="width: 300px; height: 238px" src="/laptopshop/img/'+sanpham.id+'.png">' +
									'<h3 style="font-weight: bold;">'+sanpham.name+'</h3></a>' +
									'<div class="showcauhinh" style="font-size: 12px"><table style="margin: 6%;"><tr>'+
                                    									'<td style="width: 62%;text-align:left;"><span class="cpu"><i class="fa-solid fa-microchip"></i> 'sanpham.cpu.name'</span></td>'+
                                                                        '<td style="width: 36%;text-align:left;"><span class="screen"><i class="fa-solid fa-laptop"></i> 'sanpham.screen.panel+' '+sanpham.screen.size+' inch '+touch'</span></td>'+
                                                                        '</tr><tr>'+
                                                                        '<td style="width: 64%;text-align:left;"><span class="os"><i class="fa-solid fa-gear"></i> 'sanpham.os.name'</span></td>'+
                                                                        '<td style="width: 38%;text-align:left;"><span class="ram"><i class="fa-solid fa-memory"></i> 'sanpham.ram.size' GB</span></td>'+
                                                                        '</tr></table></div>'+
									'<h3>'+accounting.formatMoney(sanpham.price)+' VND</h3>'+
									'<button  onClick="addToCart('+sanpham.id+')" class="btn btn-warning"><span class= "glyphicon glyphicon-shopping-cart pull-center"></span> Giỏ hàng</button>'+
									'<h3></h3>'+
									'</div>';
							content = section + content + endsection;
							$('.content-grids').append(content);
						}
					}
				});
			},
			error : function(e){
				alert("Error: ",e);
				console.log("Error" , e );
			}
		});
	}



})
