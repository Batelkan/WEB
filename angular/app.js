
var app = angular.module('app', ['ngMaterial'])
.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('default')
    .primaryPalette('blue')
    .accentPalette('pink');
});

 app.controller('menuBto',function(){
   this.isOpen = false;
   this.selectedMode = 'md-scale';
   this.selectedDirection = 'left';
   this.hidden = 'true';
 })

app.controller('dialogo',function($scope,$mdDialog, $mdMedia){
$scope.showAlert = function(evento){
  $mdDialog.show(
       $mdDialog.alert()
         .parent(angular.element(document.querySelector('#popupContainer')))
         .clickOutsideToClose(true)
         .title('ERROR')
         .textContent('Debe espicificar una usuario y contrasena validas!')
         .ariaLabel('Error Dialog')
         .ok('Ok !')
         .targetEvent(evento)
     );
}})

app.directive('ngFocus', [function() {
var FOCUS_CLASS = "ng-focused";
return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attrs, ctrl)
        {
          ctrl.$focused = false;
          element.bind('focus', function(evt) {
              element.addClass(FOCUS_CLASS);
              scope.$apply(function() {
              ctrl.$focused = true;});
              }).bind('blur', function(evt) {
                          element.removeClass(FOCUS_CLASS);
                          scope.$apply(function() {
                          ctrl.$focused = false;}); });
          }
      } }]);
