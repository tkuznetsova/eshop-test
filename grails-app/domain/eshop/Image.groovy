package eshop

class Image {
	byte[] photo 
	String goodId
	
	static constraints = {		
		photo maxSize: 1024 * 1024 * 2   // Limit upload file size to 2MB
	}
}
