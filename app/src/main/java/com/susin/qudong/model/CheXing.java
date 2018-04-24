package com.susin.qudong.model;

import java.io.Serializable;
import java.util.List;

public class CheXing implements Serializable{

	public String status;
	public String msg;
	public List<Car> result;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public List<Car> getResult() {
		return result;
	}

	public void setResult(List<Car> result) {
		this.result = result;
	}

	public class Car{
		public String id;
		public String name;
		public String fullname;
		public String initial;
		public String price;
		public String parentid;
		public String depth;
		public List<Car1> carlist;
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFullname() {
			return fullname;
		}

		public void setFullname(String fullname) {
			this.fullname = fullname;
		}

		public String getInitial() {
			return initial;
		}

		public void setInitial(String initial) {
			this.initial = initial;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getParentid() {
			return parentid;
		}

		public void setParentid(String parentid) {
			this.parentid = parentid;
		}

		public String getDepth() {
			return depth;
		}

		public void setDepth(String depth) {
			this.depth = depth;
		}

		public List<Car1> getCarlist() {
			return carlist;
		}

		public void setCarlist(List<Car1> carlist) {
			this.carlist = carlist;
		}

		public class Car1{
			public String id;
			public String name;
			public String fullname;
			public String initial;
			public String parentname;
			public String price;
			public String parentid;
			public String logo;
			public String salestate;
			public String depth;
			public List<Car2> list;
			
			public String getParentname() {
				return parentname;
			}

			public void setParentname(String parentname) {
				this.parentname = parentname;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getFullname() {
				return fullname;
			}

			public void setFullname(String fullname) {
				this.fullname = fullname;
			}

			public String getInitial() {
				return initial;
			}

			public void setInitial(String initial) {
				this.initial = initial;
			}

			public String getPrice() {
				return price;
			}

			public void setPrice(String price) {
				this.price = price;
			}

			public String getParentid() {
				return parentid;
			}

			public void setParentid(String parentid) {
				this.parentid = parentid;
			}

			public String getLogo() {
				return logo;
			}

			public void setLogo(String logo) {
				this.logo = logo;
			}

			public String getSalestate() {
				return salestate;
			}

			public void setSalestate(String salestate) {
				this.salestate = salestate;
			}

			public String getDepth() {
				return depth;
			}

			public void setDepth(String depth) {
				this.depth = depth;
			}

			public List<Car2> getList() {
				return list;
			}

			public void setList(List<Car2> list) {
				this.list = list;
			}

			public class Car2 implements Serializable{
				public String id;
				public String name;
				public String productionstate;
				public String initial;
				public String price;
				public String parentid;
				public String logo;
				public String salestate;
				public String depth;
				public String parentname;
				public String sizetype;
				public String yeartype;
				
				public String getParentname() {
					return parentname;
				}
				public void setParentname(String parentname) {
					this.parentname = parentname;
				}
				public String getId() {
					return id;
				}
				public void setId(String id) {
					this.id = id;
				}
				public String getName() {
					return name;
				}
				public void setName(String name) {
					this.name = name;
				}
				public String getProductionstate() {
					return productionstate;
				}
				public void setProductionstate(String productionstate) {
					this.productionstate = productionstate;
				}
				public String getInitial() {
					return initial;
				}
				public void setInitial(String initial) {
					this.initial = initial;
				}
				public String getPrice() {
					return price;
				}
				public void setPrice(String price) {
					this.price = price;
				}
				public String getParentid() {
					return parentid;
				}
				public void setParentid(String parentid) {
					this.parentid = parentid;
				}
				public String getLogo() {
					return logo;
				}
				public void setLogo(String logo) {
					this.logo = logo;
				}
				public String getSalestate() {
					return salestate;
				}
				public void setSalestate(String salestate) {
					this.salestate = salestate;
				}
				public String getDepth() {
					return depth;
				}
				public void setDepth(String depth) {
					this.depth = depth;
				}
				public String getSizetype() {
					return sizetype;
				}
				public void setSizetype(String sizetype) {
					this.sizetype = sizetype;
				}
				public String getYeartype() {
					return yeartype;
				}
				public void setYeartype(String yeartype) {
					this.yeartype = yeartype;
				}
			}
		}
	}
}
