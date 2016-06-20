
var app = angular.module('app', ['ngMaterial','ngRoute','ngScrollbars']);

app.config(['$routeProvider', function($routeProvider) {
$routeProvider
.when('/', {
templateUrl: 'views/home.html',
controller: 'HomeController'
});
}]);

app.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('default')
    .primaryPalette('blue')
    .accentPalette('pink');
});

app.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('default')
    .primaryPalette('blue')
})

app.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('TemaDark')
  .primaryPalette('grey', {
    'default': '800', // by default use shade 400 from the pink palette for primary intentions
    'hue-1': '100', // use shade 100 for the <code>md-hue-1</code> class
    'hue-2': '600', // use shade 600 for the <code>md-hue-2</code> class
    'hue-3': 'A100' // use shade A100 for the <code>md-hue-3</code> class
  })
  // If you specify less than all of the keys, it will inherit from the
  // default shades
  .accentPalette('yellow', {
    'default': '500' // use shade 200 for default, and keep all other shades the same
  });
})
