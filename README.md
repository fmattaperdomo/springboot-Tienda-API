### TIENDA EN LINEA API

##ENDPOINTS

Ruta	Funcionalidad
GET /api/productos	Mostrar todos los productos
GET /api/productos/<id producto>	Mostrar producto
POST /api/productos	Crear producto
PUT /api/productos/<id producto>	Actualizar producto
DELETE /api/productos/<id producto>	Borrar producto
GET /api/categorias/<id categoria>/productos	Mostrar todos los productos de una categoría
PUT /api/productos/<id producto>/precio/<porcentaje>	Aumenta el precio de un producto en base al porcentaje recibido
POST /api/pedidos	Crea un nuevo pedido, incluye los productos del pedido y el cliente
PUT /api/pedidos/<id pedido>	Actualiza el estado de un pedido
GET /api/productos/rezagados	Muestra los productos que no se hayan vendido (es decir que no pertenezcan a ningún pedido)
