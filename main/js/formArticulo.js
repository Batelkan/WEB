app.controller('formArticulosCtrl',function($scope)
{
  $scope.categoria=[
    "RAM",
    "CPU",
    "FUENTES DE PODER",
    "LIMPIEZA"
  ];

  $scope.deptos=[
    "Arquitectura",
    "Tesoreria",
    "Legal",
    "Cobranza",
    "Recursos Humanos",
    "Proyectos",
    "Servicios",
    "Soporte",
    "Administrativo"
  ];

  $scope.estatus=[
    "Disponible",
    "Ocupado",
    "Baja"
  ];



})
