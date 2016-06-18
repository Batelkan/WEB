app.controller('formComputadoraCtrl',function($scope)
{
  $scope.categoria=[
    "Laptop",
    "Escritorio"
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

$scope.ngScrollConfLight = {
      autoHideScrollbar: false,
      theme: 'light',
      advanced:{
          updateOnContentResize: true
      },
          setHeight: 200,
          scrollInertia: 0
      };

      $scope.ngScrollConfDark = {
            autoHideScrollbar: false,
            theme: 'dark',
            advanced:{
                updateOnContentResize: true
            },
                setHeight: 200,
                scrollInertia: 0
            };

})
