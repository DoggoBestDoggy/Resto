<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Models\Restaurant;

class RestaurantController extends Controller
{
    function index(){
        return restaurants::all();
    }

    function add(){
       $this->validate(request(), [
            'name' => 'required',
            'description' => 'required',
            'grade' => 'required',
            'localization' => 'required',
            'phone_number' => 'required',
            'website' => 'url',
            'hours' => 'required'
        ]);
        $restaurants = restaurants::create(request([
            'name',
            'description',
            'grade',
            'localization',
            'phone_number',
            'website',
            'hours'
        ]));
        return redirect()->to('/api/restaurants');
    }
}
