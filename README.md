<h1>Cooking Recipes</h1>

<h2>Una aplicación de escritorio escrita en Java</h2>
<p>Cooking Recipes es un generador de libros de recetas cocina.<p> 
<p>El usuario simplemente se crea una sesión (nombre de usuario y contraseña) y con ella puede entrar al sistema. 
Cada sesión posee una lista propia de ingredientes y una lista propia de tipos de comidas (carnes, postres, ...) que el usuario va agregando según su necesidad.<p>
<p>Existe un formulario para crear una receta en el cual se especifican:<br>

-Título de la receta.<br>
-Tipo de comida.<br>
-Cantidad de comensales.<br>
-Tiempo de preparación.<br>
-Tiempo de cocción.<br>
-Autor de la receta.<br>
-Fotografia de la receta.<br>
-Procedimiento.<br>
-Ingredientes con sus candtidades.<br>
-Consejos.<br>
<p>Una vez que se completa el formulario, dicha receta se almacena. En la ventana principal existe una tabla que muestra el listado de todas las recetas pertenecientes al usuario.</p> 
<p>Las recetas se pueden visualizar rapidamente haciendo dobleclick en la tabla, como así tambien editarlas o eliminarlas</p>
<p>Existe un buscador de recetas dinámico (encuentra a medida que se escribe en el campo de busqueda sin necesidad de presionar algún boton) que permite encontrar las recetas tanto por titulo como por tipo de comida.</p>
<p>Finalmente el usuario puede generar un libro en PDF seleccionando cuales recetas desea incluir. Además, el libro generado contiene un indice que se crea automaticamente.</p>
<h3>Requisitos:</h3>
<p>La aplicación corre tanto en Unix (MacOs, Linux) como en sistemas Windows.</p>
<p>Requiere tener JVM instalada.</p>
<h3>Especificaciones técnicas:</h3>
<p>Por defecto es portable (con una base de datos SQLite embebida), pero también se puede conectar a una base de datos MySql directamente desde la aplicación sin necesidad de recompilar. Esto se hace mediante una opcion habilitada solo para una cuenta "administrador" [<b>admin</b>, <b>cookingadmin</b>], en ésta opcion existe un formulario donde se cargan los datos de conexión de Mysql (Username, Password, Host y Database).</p>
<p>Dentro de la clase "modelo.recuros.databases" se encuentra el fichero <b>cooking.sql</b> para Mysql y el fichero <b>cook.db</b> para SQLite.</p> 
