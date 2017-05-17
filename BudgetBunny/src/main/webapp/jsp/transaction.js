//$ lessc build/build_standalone.less datepicker.css

$('.datepicker').datepicker({
    format: 'mm/dd/yyyy',
    startDate: '-3d'
});

