let enviaRegistro = document.getElementById("btnRegistro");

enviaRegistro.addEventListener("click", evento=>{
    registraCliente();
})

let registraCliente = async() => {

    let datosCliente = {};

    datosCliente.nombre     = document.getElementById("nombre").value;
    datosCliente.apellidoP  = document.getElementById("apellidoP").value;
    datosCliente.apellidoM  = document.getElementById("apellidoM").value;
    datosCliente.correo     = document.getElementById("correo").value;
    datosCliente.calle      = document.getElementById("calle").value;
    datosCliente.numero     = document.getElementById("numero").value;
    datosCliente.colonia    = document.getElementById("colonia").value;
    datosCliente.delegacion = document.getElementById("delegacion").value;
    datosCliente.ciudad     = document.getElementById("ciudad").value;
    datosCliente.cp         = document.getElementById("cp").value;

    const peticion = await fetch("http://localhost:8080/cliente/registraCliente",
    {
        method:  'POST',
        headers: {
            'Accept':       'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(campos)
    });
}
