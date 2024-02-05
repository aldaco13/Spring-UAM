/*let enviaRegistro = document.getElementById("btnRegistro");
enviaRegistro.addEventListener('click', evento => {
    muestraModal();
    let continua = document.getElementById("continuar");
    let avanza = false
    continua.addEventListener('click', evento => {
        alert('boton continuar');
        avanza = continuar();
        if(avanza){
            alert('avanzar');
        }
    })
    

    //miFunciom();
    //alert('Desea continuar?');

})*/


function miFunciom(){
    return datos = enviarDatosAlBackend();
}

async function enviarDatosAlBackend() {
    // Datos que deseas enviar al backend
    const cliente = {
        cliente: document.getElementById("numCliente").value,
        nombre: document.getElementById("nombre").value,
        apellidoPaterno: document.getElementById("apellidoP").value,
        apellidoMaterno: document.getElementById("apellidoM").value,
        correo: document.getElementById("correo").value,
        calle: document.getElementById("calle").value,
        numero: document.getElementById("numero").value,
        colonia: document.getElementById("colonia").value,
        delegacion: document.getElementById("delegacion").value,
        cp: document.getElementById("cp").value,
        ciudad: document.getElementById("ciudad").value
    };
    
    return new Promise((resolve, reject) => {
      // Realizar la petición POST
        fetch('http://127.0.0.1:8080/cliente/registraCliente', {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json',
            },
            body: JSON.stringify(cliente),
        })
        .then(respuesta => {
          // Verificar si la respuesta fue exitosa (código 2xx)
            if (respuesta.status == 200) {
                // Procesar la respuesta JSON
                alert('Se registró');
                return respuesta.json();
            } else if(respuesta.status == 400){
                alert('El usuario ya existe');
                throw new Error(`Error en la petición: ${respuesta.status} ${respuesta.statusText}`);
            }else{
                 // Rechazar la promesa en caso de error
                 alert('Error en el envio de los datos');
                throw new Error(`Error en la petición: ${respuesta.status} ${respuesta.statusText}`);
            }
        })
        .then(datosRespuesta => {
            // Resolver la promesa con los datos recibidos
            resolve(datosRespuesta);
        })
        .catch(error => {
            // Rechazar la promesa en caso de cualquier error
            reject(error);
        });
    });
}
  
function muestraModal(){
    alert('mostrar modal')
    let modal = document.getElementById("continuar");
    let cliente = document.getElementById("numCliente");
    alert(modal + cliente);
    //modal.style.display = 'flex';
}

function mostrarLoading() {
    console.log('Cargando...');
  
    setTimeout(function() {
        console.log('Carga completada.');
    }, 5000);
}

function continuar(){
    return true;
}