<template>
    <div class="text-center my-7">
      <img
        src="/worknest-logo.ico"
        class="d-inline-flex"
        style="max-height: 100px"
        align="center"
      />
      <p
        v-if="!bookingId"
        class="text-h3 d-inline font-italic ml-5"
        style="vertical-align: middle"
      >
        Book desks for your team
      </p>
      <p
        v-else
        class="text-h3 d-inline font-italic ml-5"
        style="vertical-align: middle"
      >
        Modify your booking
      </p>
    </div>
    <Transition
      enter-active-class="animate__animated animate__fadeIn"
      enter-leave-class="animate__animated animate__fadeOut"
      appear
    >
      <v-row justify="center" v-show="!isSvgVisible">
        <v-col cols="12" md="8" lg="6">
          <v-card class="mb-4 d-block rounded-lg" elevation="2" align="center">
            <v-card-text>
              <v-date-picker
                v-model="this.selectedDate"
                :min="new Date(new Date().setDate(new Date().getDate() - 1))"
                no-title
                scrollable
              ></v-date-picker>
            </v-card-text>
            <v-card-actions>
              <v-btn
                color="primary"
                class="mx-auto rounded-pill"
                @click="findOccupiedDesks()"
                >Find Available Desks</v-btn
              >
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </Transition>
  
    <v-row>
      <v-col cols="12" md="6" style="max-width: 400px">
        <Transition
          enter-active-class="animate__animated animate__backInUp"
          leave-active-class="animate__animated animate__backOutDown"
        >
          <v-btn
            size="x-large"
            icon="mdi-calendar"
            class="d-flex position-fixed ml-auto"
            color="blue-grey-lighten-4"
            style="bottom: 2%; right: 2%"
            @click="isSvgVisible = false"
            v-if="isSvgVisible"
            elevation="12"
          >
          </v-btn>
        </Transition>
      </v-col>


      <v-col cols="12" md="6">
        <div class="svg-container" style="text-align: center">
          <Transition enter-active-class="animate__animated animate__fadeIn">
            <svg 
            v-show="isSvgVisible"
            width="1800.0000000000005" height="900.0000000000002" xmlns="http://www.w3.org/2000/svg">
 <defs>
  <filter id="svg_21_blur">
   <feGaussianBlur in="SourceGraphic" stdDeviation="0"/>
  </filter>
 </defs>

  <title>Layer 1</title>
  <line stroke="#000" fill="none" x1="17" y1="544.85696" x2="17" y2="869.0808" id="svg_5" stroke-linejoin="undefined" stroke-linecap="undefined"/>
  <line stroke="#000" fill="none" x1="18.62617" y1="693.57434" x2="80.53099" y2="693.57434" id="svg_256"/>
  <line fill="none" x1="1569.65662" y1="679.32521" x2="1633.06002" y2="679.32521" id="svg_336" stroke="#000"/>
  <line stroke="#000" fill="none" x1="1769.58817" y1="53.85561" x2="1769.58817" y2="98.52832" id="svg_19" stroke-linejoin="undefined" stroke-linecap="undefined"/>
  <text fill="#000000" stroke="#000" x="1621.65007" y="780.08248" id="svg_88" stroke-width="0" font-size="24" font-family="Noto Sans JP" text-anchor="start" xml:space="preserve">Bathroom</text>
  <line stroke="#000" fill="none" x1="1348.54154" y1="378.35831" x2="1348.54154" y2="54.69868" id="svg_261"/>
  <line stroke="#000" fill="none" x1="1718.36658" y1="380.45383" x2="1768.24476" y2="380.45383" id="svg_263"/>
  <line fill="none" x1="17.08416" y1="362.89521" x2="17.08416" y2="118.71216" id="svg_601" stroke-dasharray="5,5" stroke="#000"/>
  <line stroke="#000" fill="none" x1="1516.88832" y1="869.3908" x2="1769.84474" y2="869.3908" id="svg_604" stroke-linejoin="undefined" stroke-linecap="undefined"/>
  <line stroke="#000" fill="none" x1="1771.01674" y1="513.8539" x2="1771.01674" y2="869.0818" id="svg_607" stroke-linejoin="undefined" stroke-linecap="undefined"/>
  <line stroke="#000" fill="none" x1="881.04154" y1="380.05047" x2="881.04154" y2="52.35138" id="svg_707"/>
  <line stroke="#000" fill="none" x1="18.24929" y1="52.33351" x2="76.91021" y2="52.33351" id="svg_809"/>
  <line stroke="#000" fill="none" x1="839.1482" y1="52.3674" x2="86.70132" y2="52.3674" id="svg_1031" stroke-dasharray="5,5"/>
  <line fill="none" x1="1714.38817" y1="52.53678" x2="1769.84473" y2="52.53678" id="svg_1032" stroke-linejoin="undefined" stroke-linecap="undefined" stroke="#000"/>
  <line fill="none" x1="1711.14312" y1="680.67656" x2="1771.84382" y2="680.67656" id="svg_1176" stroke="#000"/>
  <line stroke="#000" fill="none" x1="276.14119" y1="870.66494" x2="1516.47633" y2="870.66494" id="svg_1177" stroke-linejoin="undefined" stroke-linecap="undefined" stroke-dasharray="5,5"/>
  <line stroke="#000" fill="none" x1="17.35135" y1="869.3908" x2="270.56304" y2="869.3908" id="svg_1178" stroke-linejoin="undefined" stroke-linecap="undefined"/>
  <line stroke="#000" fill="none" x1="17.08416" y1="114.42325" x2="17.08416" y2="51.94106" id="svg_1299"/>
  <g id="svg_1515">
   <rect fill="#f2efef" x="539.92132" y="736.73206" width="28" height="56" id="svg_1511" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="568.17116" y="751.48206" width="13" height="25" id="svg_1512" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="556.75464" cy="764.3035" id="svg_1513" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="556.75464" cy="764.3035" id="svg_1514" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_1520">
   <rect fill="#f2efef" x="501.58796" y="736.70428" width="28" height="56" id="svg_1516" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="488.58796" y="751.56142" width="13" height="25" id="svg_1517" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="512.75463" cy="764.28761" id="svg_1518" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="512.75463" cy="764.28761" id="svg_1519" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_1535">
   <rect fill="#f2efef" x="372.35375" y="736.73206" width="28" height="56" id="svg_1531" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="400.60359" y="751.48206" width="13" height="25" id="svg_1532" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="389.18707" cy="764.3035" id="svg_1533" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="389.18707" cy="764.3035" id="svg_1534" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_1540">
   <rect fill="#f2efef" x="334.02039" y="736.70428" width="28" height="56" id="svg_1536" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="321.02039" y="751.56142" width="13" height="25" id="svg_1537" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="345.18706" cy="764.28761" id="svg_1538" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="345.18706" cy="764.28761" id="svg_1539" rx="12" ry="12" stroke="#000"/>
  </g>
  <line stroke="#000" fill="none" x1="228.83553" y1="867.80338" x2="228.83553" y2="694.13899" id="svg_1563"/>
  <text fill="#000000" stroke="#000" x="74.38762" y="789.20405" id="svg_1565" stroke-width="0" font-size="24" font-family="Noto Sans JP" text-anchor="start" xml:space="preserve">Bathroom</text>
  <line stroke="#000" fill="none" x1="1712.56041" y1="52.3674" x2="1403.5934" y2="52.3674" id="svg_1566" stroke-dasharray="5,5"/>
  <line stroke="#000" fill="none" x1="1769.58817" y1="101.23823" x2="1769.58817" y2="332.8755" id="svg_1569" stroke-linejoin="undefined" stroke-linecap="undefined" stroke-dasharray="5,5"/>
  <line stroke="#000" fill="none" x1="1281.28" y1="52.53678" x2="1416.46637" y2="52.53678" id="svg_1570" stroke-linejoin="undefined" stroke-linecap="undefined"/>
  <line fill="none" x1="1571.01695" y1="680.10356" x2="1571.01695" y2="869.08179" id="svg_1" stroke-linejoin="undefined" stroke-linecap="undefined" stroke="#000"/>
  <g id="meeting_room">
   <rect stroke="#000" fill="#f2efef" x="1432.25789" y="131.40516" width="264.95057" height="170.36876" id="svg_610" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1509.13754" y="157.69242" width="115.55446" height="115.55446" id="svg_611" rx="8"/>
   <rect fill="#3f7f00" x="1512.84121" y="161.39609" width="108.14713" height="108.14713" id="svg_612" rx="8" stroke="#000" data-id = "103ef45c-ee00-433f-aa62-6a298afe32ec" @click="bookingDesk($event)"/>
   <rect fill="#f2efef" stroke="#000" x="1465.29589" y="110.80764" width="43.74976" height="21.24988" id="svg_616" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1469.85709" y="302.05658" width="43.74976" height="21.24988" id="svg_620" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1544.85668" y="302.05658" width="43.74976" height="21.24988" id="svg_621" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1406.47956" y="187.90178" width="24.99986" height="48.74973" id="svg_623" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1696.47782" y="187.15856" width="24.99986" height="48.74973" id="svg_624" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1545.02563" y="110.80764" width="43.74976" height="21.24988" id="svg_1093" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1618.23509" y="301.5509" width="43.74976" height="21.24988" id="svg_1094" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1617.9986" y="110.80764" width="43.74976" height="21.24988" id="svg_1561" rx="8"/>
  </g>
  <g id="svg_11">
   <rect fill="#f2efef" x="539.89568" y="649.73206" width="28" height="56" id="svg_6" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="568.14552" y="664.48206" width="13" height="25" id="svg_8" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="556.729" cy="677.3035" id="svg_9" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="556.729" cy="677.3035" id="svg_10" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_16">
   <rect fill="#f2efef" x="501.56232" y="649.70428" width="28" height="56" id="svg_12" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="488.56232" y="664.56142" width="13" height="25" id="svg_13" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="512.72899" cy="677.28761" id="svg_14" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="512.72899" cy="677.28761" id="svg_15" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_22">
   <rect fill="#f2efef" x="539.92132" y="586.73206" width="28" height="56" id="svg_17" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="568.17116" y="601.48206" width="13" height="25" id="svg_18" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="556.75464" cy="614.3035" id="svg_20" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="556.75464" cy="614.3035" id="svg_21" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_27">
   <rect fill="#f2efef" x="501.58796" y="586.70428" width="28" height="56" id="svg_23" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="488.58796" y="601.56142" width="13" height="25" id="svg_24" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="512.75463" cy="614.28761" id="svg_25" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="512.75463" cy="614.28761" id="svg_26" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_32">
   <rect fill="#f2efef" x="372.32811" y="649.73206" width="28" height="56" id="svg_28" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="400.57795" y="664.48206" width="13" height="25" id="svg_29" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="389.16143" cy="677.3035" id="svg_30" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="389.16143" cy="677.3035" id="svg_31" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_37">
   <rect fill="#f2efef" x="333.99475" y="649.70428" width="28" height="56" id="svg_33" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="320.99475" y="664.56142" width="13" height="25" id="svg_34" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="345.16142" cy="677.28761" id="svg_35" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="345.16142" cy="677.28761" id="svg_36" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_42">
   <rect fill="#f2efef" x="372.35375" y="586.73206" width="28" height="56" id="svg_38" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="400.60359" y="601.48206" width="13" height="25" id="svg_39" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="389.18707" cy="614.3035" id="svg_40" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="389.18707" cy="614.3035" id="svg_41" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_47">
   <rect fill="#f2efef" x="334.02039" y="586.70428" width="28" height="56" id="svg_43" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="321.02039" y="601.56142" width="13" height="25" id="svg_44" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="345.18706" cy="614.28761" id="svg_45" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="345.18706" cy="614.28761" id="svg_46" rx="12" ry="12" stroke="#000"/>
  </g>
  <line stroke="#000" fill="none" x1="1769.58817" y1="341.35561" x2="1769.58817" y2="407.27832" id="svg_312" stroke-linejoin="undefined" stroke-linecap="undefined"/>
  <line fill="none" x1="17.08416" y1="465.67324" x2="17.08416" y2="372.77438" id="svg_313" stroke="#000"/>
  <g id="svg_319">
   <rect fill="#f2efef" x="288.64568" y="307.23206" width="28" height="56" id="svg_315" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="316.89552" y="321.98206" width="13" height="25" id="svg_316" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="305.479" cy="334.8035" id="svg_317" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="305.479" cy="334.8035" id="svg_318" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_324">
   <rect fill="#f2efef" x="250.31232" y="307.20428" width="28" height="56" id="svg_320" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="237.31232" y="322.06142" width="13" height="25" id="svg_321" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="261.47899" cy="334.78761" id="svg_322" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="261.47899" cy="334.78761" id="svg_323" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_329">
   <rect fill="#f2efef" x="288.67132" y="244.23206" width="28" height="56" id="svg_325" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="316.92116" y="258.98206" width="13" height="25" id="svg_326" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="305.50464" cy="271.8035" id="svg_327" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="305.50464" cy="271.8035" id="svg_328" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_334">
   <rect fill="#f2efef" x="250.33796" y="244.20428" width="28" height="56" id="svg_330" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="237.33796" y="259.06142" width="13" height="25" id="svg_331" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="261.50463" cy="271.78761" id="svg_332" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="261.50463" cy="271.78761" id="svg_333" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_340">
   <rect fill="#f2efef" x="121.07811" y="307.23206" width="28" height="56" id="svg_335" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="149.32795" y="321.98206" width="13" height="25" id="svg_337" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="137.91143" cy="334.8035" id="svg_338" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="137.91143" cy="334.8035" id="svg_339" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_350">
   <rect fill="#f2efef" x="121.10375" y="244.23206" width="28" height="56" id="svg_346" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="149.35359" y="258.98206" width="13" height="25" id="svg_347" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="137.93707" cy="271.8035" id="svg_348" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="137.93707" cy="271.8035" id="svg_349" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_360">
   <rect fill="#f2efef" x="288.64568" y="157.23206" width="28" height="56" id="svg_356" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="316.89552" y="171.98206" width="13" height="25" id="svg_357" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="305.479" cy="184.8035" id="svg_358" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="305.479" cy="184.8035" id="svg_359" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_365">
   <rect fill="#f2efef" x="250.31232" y="157.20428" width="28" height="56" id="svg_361" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="237.31232" y="172.06142" width="13" height="25" id="svg_362" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="261.47899" cy="184.78761" id="svg_363" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="261.47899" cy="184.78761" id="svg_364" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_380">
   <rect fill="#f2efef" x="121.07811" y="157.23206" width="28" height="56" id="svg_376" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="149.32795" y="171.98206" width="13" height="25" id="svg_377" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="137.91143" cy="184.8035" id="svg_378" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="137.91143" cy="184.8035" id="svg_379" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_390">
   <rect fill="#f2efef" x="1261.10375" y="797.23206" width="28" height="56" id="svg_386" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1289.35359" y="811.98206" width="13" height="25" id="svg_387" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1277.93707" cy="824.8035" id="svg_388" rx="16" ry="16" stroke="#000" class="dx window"/>
   <ellipse fill="#3f7f00" cx="1277.93707" cy="824.8035" id="svg_389" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_395">
   <rect fill="#f2efef" x="1222.77039" y="797.20428" width="28" height="56" id="svg_391" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1209.77039" y="812.06142" width="13" height="25" id="svg_392" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1233.93706" cy="824.78761" id="svg_393" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="1233.93706" cy="824.78761" id="svg_394" rx="12" ry="12" stroke="#000"/>
  </g>
  <line stroke="#000" fill="none" x1="137.37617" y1="693.57434" x2="228.03099" y2="693.57434" id="svg_396"/>
  <g id="meeting_room">
   <rect stroke="#000" fill="#f2efef" x="989.75789" y="132.65516" width="264.95057" height="170.36876" id="svg_4" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1066.63754" y="158.94242" width="115.55446" height="115.55446" id="svg_48" rx="8"/>
   <rect data-id = "fda6463e-96d3-4eb1-9e62-1fcbf8b99d38" @click="bookingDesk($event)" fill="#3f7f00" x="1070.34121" y="162.64609" width="108.14713" height="108.14713" id="svg_49" rx="8" stroke="#000" />
   <rect fill="#f2efef" stroke="#000" x="1022.79589" y="112.05764" width="43.74976" height="21.24988" id="svg_50" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1027.35709" y="303.30658" width="43.74976" height="21.24988" id="svg_51" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1102.35668" y="303.30658" width="43.74976" height="21.24988" id="svg_52" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="963.97956" y="189.15178" width="24.99986" height="48.74973" id="svg_53" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1253.97782" y="188.40856" width="24.99986" height="48.74973" id="svg_54" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1102.52563" y="112.05764" width="43.74976" height="21.24988" id="svg_55" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1175.73509" y="302.8009" width="43.74976" height="21.24988" id="svg_56" rx="8"/>
   <rect fill="#f2efef" stroke="#000" x="1175.4986" y="112.05764" width="43.74976" height="21.24988" id="svg_57" rx="8"/>
  </g>
  <line stroke="#000" fill="none" x1="1277.56039" y1="52.3674" x2="943.5934" y2="52.3674" id="svg_59" stroke-dasharray="5,5"/>
  <line stroke="#000" fill="none" x1="845.7493" y1="52.33351" x2="939.41021" y2="52.33351" id="svg_60"/>
  <line stroke="#000" fill="none" x1="998.36661" y1="380.45383" x2="1655.74481" y2="380.45383" id="svg_61"/>
  <line stroke="#000" fill="none" x1="880.86664" y1="380.45383" x2="940.74476" y2="380.45383" id="svg_62"/>
  <g id="svg_67">
   <rect fill="#f2efef" x="604.89568" y="307.23206" width="28" height="56" id="svg_63" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="633.14552" y="321.98206" width="13" height="25" id="svg_64" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="621.729" cy="334.8035" id="svg_65" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="621.729" cy="334.8035" id="svg_66" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_72">
   <rect fill="#f2efef" x="566.56232" y="307.20428" width="28" height="56" id="svg_68" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="553.56232" y="322.06142" width="13" height="25" id="svg_69" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="577.72899" cy="334.78761" id="svg_70" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="577.72899" cy="334.78761" id="svg_71" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_77">
   <rect fill="#f2efef" x="604.92132" y="244.23206" width="28" height="56" id="svg_73" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="633.17116" y="258.98206" width="13" height="25" id="svg_74" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="621.75464" cy="271.8035" id="svg_75" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="621.75464" cy="271.8035" id="svg_76" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_82">
   <rect fill="#f2efef" x="566.58796" y="244.20428" width="28" height="56" id="svg_78" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="553.58796" y="259.06142" width="13" height="25" id="svg_79" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="577.75463" cy="271.78761" id="svg_80" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="577.75463" cy="271.78761" id="svg_81" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_87">
   <rect fill="#f2efef" x="437.32811" y="307.23206" width="28" height="56" id="svg_83" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="465.57795" y="321.98206" width="13" height="25" id="svg_84" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="454.16143" cy="334.8035" id="svg_85" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="454.16143" cy="334.8035" id="svg_86" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_93">
   <rect fill="#f2efef" x="398.99475" y="307.20428" width="28" height="56" id="svg_89" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="385.99475" y="322.06142" width="13" height="25" id="svg_90" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="410.16142" cy="334.78761" id="svg_91" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="410.16142" cy="334.78761" id="svg_92" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_98">
   <rect fill="#f2efef" x="437.35375" y="244.23206" width="28" height="56" id="svg_94" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="465.60359" y="258.98206" width="13" height="25" id="svg_95" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="454.18707" cy="271.8035" id="svg_96" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="454.18707" cy="271.8035" id="svg_97" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_103">
   <rect fill="#f2efef" x="399.02039" y="244.20428" width="28" height="56" id="svg_99" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="386.02039" y="259.06142" width="13" height="25" id="svg_100" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="410.18706" cy="271.78761" id="svg_101" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="410.18706" cy="271.78761" id="svg_102" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_108">
   <rect fill="#f2efef" x="604.89568" y="157.23206" width="28" height="56" id="svg_104" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="633.14552" y="171.98206" width="13" height="25" id="svg_105" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="621.729" cy="184.8035" id="svg_106" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="621.729" cy="184.8035" id="svg_107" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_113">
   <rect fill="#f2efef" x="566.56232" y="157.20428" width="28" height="56" id="svg_109" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="553.56232" y="172.06142" width="13" height="25" id="svg_110" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="577.72899" cy="184.78761" id="svg_111" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="577.72899" cy="184.78761" id="svg_112" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_128">
   <rect fill="#f2efef" x="437.32811" y="157.23206" width="28" height="56" id="svg_124" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="465.57795" y="171.98206" width="13" height="25" id="svg_125" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="454.16143" cy="184.8035" id="svg_126" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="454.16143" cy="184.8035" id="svg_127" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_133">
   <rect fill="#f2efef" x="398.99475" y="157.20428" width="28" height="56" id="svg_129" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="385.99475" y="172.06142" width="13" height="25" id="svg_130" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="410.16142" cy="184.78761" id="svg_131" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="410.16142" cy="184.78761" id="svg_132" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_148">
   <rect fill="#f2efef" x="756.14568" y="307.23206" width="28" height="56" id="svg_144" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="784.39552" y="321.98206" width="13" height="25" id="svg_145" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="772.979" cy="334.8035" id="svg_146" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="772.979" cy="334.8035" id="svg_147" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_153">
   <rect fill="#f2efef" x="717.81232" y="307.20428" width="28" height="56" id="svg_149" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="704.81232" y="322.06142" width="13" height="25" id="svg_150" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="728.97899" cy="334.78761" id="svg_151" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="728.97899" cy="334.78761" id="svg_152" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_158">
   <rect fill="#f2efef" x="756.17132" y="244.23206" width="28" height="56" id="svg_154" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="784.42116" y="258.98206" width="13" height="25" id="svg_155" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="773.00464" cy="271.8035" id="svg_156" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="773.00464" cy="271.8035" id="svg_157" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_163">
   <rect fill="#f2efef" x="717.83796" y="244.20428" width="28" height="56" id="svg_159" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="704.83796" y="259.06142" width="13" height="25" id="svg_160" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="729.00463" cy="271.78761" id="svg_161" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="729.00463" cy="271.78761" id="svg_162" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_168">
   <rect fill="#f2efef" x="756.14568" y="157.23206" width="28" height="56" id="svg_164" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="784.39552" y="171.98206" width="13" height="25" id="svg_165" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="772.979" cy="184.8035" id="svg_166" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="772.979" cy="184.8035" id="svg_167" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_173">
   <rect fill="#f2efef" x="717.81232" y="157.20428" width="28" height="56" id="svg_169" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="704.81232" y="172.06142" width="13" height="25" id="svg_170" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="728.97899" cy="184.78761" id="svg_171" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="728.97899" cy="184.78761" id="svg_172" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_198">
   <rect fill="#f2efef" x="899.92132" y="734.23206" width="28" height="56" id="svg_194" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="928.17116" y="748.98206" width="13" height="25" id="svg_195" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="916.75464" cy="761.8035" id="svg_196" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="916.75464" cy="761.8035" id="svg_197" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_203">
   <rect fill="#f2efef" x="861.58796" y="734.20428" width="28" height="56" id="svg_199" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="848.58796" y="749.06142" width="13" height="25" id="svg_200" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="872.75463" cy="761.78761" id="svg_201" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="872.75463" cy="761.78761" id="svg_202" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_218">
   <rect fill="#f2efef" x="732.35375" y="734.23206" width="28" height="56" id="svg_214" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="760.60359" y="748.98206" width="13" height="25" id="svg_215" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="749.18707" cy="761.8035" id="svg_216" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="749.18707" cy="761.8035" id="svg_217" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_223">
   <rect fill="#f2efef" x="694.02039" y="734.20428" width="28" height="56" id="svg_219" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="681.02039" y="749.06142" width="13" height="25" id="svg_220" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="705.18706" cy="761.78761" id="svg_221" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="705.18706" cy="761.78761" id="svg_222" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_228">
   <rect fill="#f2efef" x="899.89568" y="647.23206" width="28" height="56" id="svg_224" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="928.14552" y="661.98206" width="13" height="25" id="svg_225" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="916.729" cy="674.8035" id="svg_226" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="916.729" cy="674.8035" id="svg_227" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_233">
   <rect fill="#f2efef" x="861.56232" y="647.20428" width="28" height="56" id="svg_229" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="848.56232" y="662.06142" width="13" height="25" id="svg_230" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="872.72899" cy="674.78761" id="svg_231" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="872.72899" cy="674.78761" id="svg_232" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_238">
   <rect fill="#f2efef" x="899.92132" y="584.23206" width="28" height="56" id="svg_234" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="928.17116" y="598.98206" width="13" height="25" id="svg_235" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="916.75464" cy="611.8035" id="svg_236" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="916.75464" cy="611.8035" id="svg_237" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_243">
   <rect fill="#f2efef" x="861.58796" y="584.20428" width="28" height="56" id="svg_239" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="848.58796" y="599.06142" width="13" height="25" id="svg_240" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="872.75463" cy="611.78761" id="svg_241" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="872.75463" cy="611.78761" id="svg_242" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_248">
   <rect fill="#f2efef" x="732.32811" y="647.23206" width="28" height="56" id="svg_244" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="760.57795" y="661.98206" width="13" height="25" id="svg_245" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="749.16143" cy="674.8035" id="svg_246" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="749.16143" cy="674.8035" id="svg_247" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_253">
   <rect fill="#f2efef" x="693.99475" y="647.20428" width="28" height="56" id="svg_249" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="680.99475" y="662.06142" width="13" height="25" id="svg_250" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="705.16142" cy="674.78761" id="svg_251" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="705.16142" cy="674.78761" id="svg_252" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_259">
   <rect fill="#f2efef" x="732.35375" y="584.23206" width="28" height="56" id="svg_254" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="760.60359" y="598.98206" width="13" height="25" id="svg_255" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="749.18707" cy="611.8035" id="svg_257" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="749.18707" cy="611.8035" id="svg_258" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_266">
   <rect fill="#f2efef" x="694.02039" y="584.20428" width="28" height="56" id="svg_260" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="681.02039" y="599.06142" width="13" height="25" id="svg_262" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="705.18706" cy="611.78761" id="svg_264" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="705.18706" cy="611.78761" id="svg_265" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_281">
   <rect fill="#f2efef" x="1261.17132" y="731.73206" width="28" height="56" id="svg_277" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1289.42116" y="746.48206" width="13" height="25" id="svg_278" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1278.00464" cy="759.3035" id="svg_279" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="1278.00464" cy="759.3035" id="svg_280" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_286">
   <rect fill="#f2efef" x="1222.83796" y="731.70428" width="28" height="56" id="svg_282" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1209.83796" y="746.56142" width="13" height="25" id="svg_283" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1234.00463" cy="759.28761" id="svg_284" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="1234.00463" cy="759.28761" id="svg_285" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_301">
   <rect fill="#f2efef" x="1093.60375" y="731.73206" width="28" height="56" id="svg_297" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1121.85359" y="746.48206" width="13" height="25" id="svg_298" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1110.43707" cy="759.3035" id="svg_299" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="1110.43707" cy="759.3035" id="svg_300" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_306">
   <rect fill="#f2efef" x="1055.27039" y="731.70428" width="28" height="56" id="svg_302" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1042.27039" y="746.56142" width="13" height="25" id="svg_303" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1066.43706" cy="759.28761" id="svg_304" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="1066.43706" cy="759.28761" id="svg_305" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_311">
   <rect fill="#f2efef" x="1261.14568" y="644.73206" width="28" height="56" id="svg_307" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1289.39552" y="659.48206" width="13" height="25" id="svg_308" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1277.979" cy="672.3035" id="svg_309" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="1277.979" cy="672.3035" id="svg_310" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_400">
   <rect fill="#f2efef" x="1222.81232" y="644.70428" width="28" height="56" id="svg_314" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1209.81232" y="659.56142" width="13" height="25" id="svg_397" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1233.97899" cy="672.28761" id="svg_398" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="1233.97899" cy="672.28761" id="svg_399" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_405">
   <rect fill="#f2efef" x="1261.17132" y="581.73206" width="28" height="56" id="svg_401" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1289.42116" y="596.48206" width="13" height="25" id="svg_402" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1278.00464" cy="609.3035" id="svg_403" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="1278.00464" cy="609.3035" id="svg_404" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_410">
   <rect fill="#f2efef" x="1222.83796" y="581.70428" width="28" height="56" id="svg_406" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1209.83796" y="596.56142" width="13" height="25" id="svg_407" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1234.00463" cy="609.28761" id="svg_408" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="1234.00463" cy="609.28761" id="svg_409" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_415">
   <rect fill="#f2efef" x="1093.57811" y="644.73206" width="28" height="56" id="svg_411" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1121.82795" y="659.48206" width="13" height="25" id="svg_412" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1110.41143" cy="672.3035" id="svg_413" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="1110.41143" cy="672.3035" id="svg_414" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_420">
   <rect fill="#f2efef" x="1055.24475" y="644.70428" width="28" height="56" id="svg_416" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1042.24475" y="659.56142" width="13" height="25" id="svg_417" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1066.41142" cy="672.28761" id="svg_418" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="1066.41142" cy="672.28761" id="svg_419" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_425">
   <rect fill="#f2efef" x="1093.60375" y="581.73206" width="28" height="56" id="svg_421" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1121.85359" y="596.48206" width="13" height="25" id="svg_422" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1110.43707" cy="609.3035" id="svg_423" rx="16" ry="16" stroke="#000" class="dx"/>
   <ellipse fill="#3f7f00" cx="1110.43707" cy="609.3035" id="svg_424" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_430">
   <rect fill="#f2efef" x="1055.27039" y="581.70428" width="28" height="56" id="svg_426" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1042.27039" y="596.56142" width="13" height="25" id="svg_427" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1066.43706" cy="609.28761" id="svg_428" rx="16" ry="16" stroke="#000" class="sx"/>
   <ellipse fill="#3f7f00" cx="1066.43706" cy="609.28761" id="svg_429" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_115">
   <rect fill="#f2efef" x="731.10375" y="797.23206" width="28" height="56" id="svg_2" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="759.35359" y="811.98206" width="13" height="25" id="svg_3" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="747.93707" cy="824.8035" id="svg_7" rx="16" ry="16" stroke="#000" class="dx window"/>
   <ellipse fill="#3f7f00" cx="747.93707" cy="824.8035" id="svg_114" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_120">
   <rect fill="#f2efef" x="692.77039" y="797.20428" width="28" height="56" id="svg_116" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="679.77039" y="812.06142" width="13" height="25" id="svg_117" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="703.93706" cy="824.78761" id="svg_118" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="703.93706" cy="824.78761" id="svg_119" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_135">
   <rect fill="#f2efef" x="540.10375" y="801.23206" width="28" height="56" id="svg_121" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="568.35359" y="815.98206" width="13" height="25" id="svg_122" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="556.93707" cy="828.8035" id="svg_123" rx="16" ry="16" stroke="#000" class="dx window"/>
   <ellipse fill="#3f7f00" cx="556.93707" cy="828.8035" id="svg_134" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_140">
   <rect fill="#f2efef" x="501.77039" y="801.20428" width="28" height="56" id="svg_136" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="488.77039" y="816.06142" width="13" height="25" id="svg_137" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="512.93706" cy="828.78761" id="svg_138" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="512.93706" cy="828.78761" id="svg_139" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_175">
   <rect fill="#f2efef" x="372.10375" y="800.23206" width="28" height="56" id="svg_141" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="400.35359" y="814.98206" width="13" height="25" id="svg_142" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="388.93707" cy="827.8035" id="svg_143" rx="16" ry="16" stroke="#000" class="dx window"/>
   <ellipse fill="#3f7f00" cx="388.93707" cy="827.8035" id="svg_174" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_180">
   <rect fill="#f2efef" x="82.77039" y="306.20428" width="28" height="56" id="svg_176" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="69.77039" y="321.06142" width="13" height="25" id="svg_177" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="93.93706" cy="333.78761" id="svg_178" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="93.93706" cy="333.78761" id="svg_179" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_185">
   <rect fill="#f2efef" x="900.10375" y="798.23206" width="28" height="56" id="svg_181" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="928.35359" y="812.98206" width="13" height="25" id="svg_182" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="916.93707" cy="825.8035" id="svg_183" rx="16" ry="16" stroke="#000" class="dx window"/>
   <ellipse fill="#3f7f00" cx="916.93707" cy="825.8035" id="svg_184" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_190">
   <rect fill="#f2efef" x="861.77039" y="798.20428" width="28" height="56" id="svg_186" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="848.77039" y="813.06142" width="13" height="25" id="svg_187" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="872.93706" cy="825.78761" id="svg_188" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="872.93706" cy="825.78761" id="svg_189" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_205">
   <rect fill="#f2efef" x="82.77039" y="244.20428" width="28" height="56" id="svg_191" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="69.77039" y="259.06142" width="13" height="25" id="svg_192" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="93.93706" cy="271.78761" id="svg_193" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="93.93706" cy="271.78761" id="svg_204" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_210">
   <rect fill="#f2efef" x="82.77039" y="156.20428" width="28" height="56" id="svg_206" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="69.77039" y="171.06142" width="13" height="25" id="svg_207" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="93.93706" cy="183.78761" id="svg_208" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="93.93706" cy="183.78761" id="svg_209" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_268">
   <rect fill="#f2efef" x="333.77039" y="800.20428" width="28" height="56" id="svg_211" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="320.77039" y="815.06142" width="13" height="25" id="svg_212" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="344.93706" cy="827.78761" id="svg_213" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="344.93706" cy="827.78761" id="svg_267" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_273">
   <rect fill="#f2efef" x="755.10375" y="95.23206" width="28" height="56" id="svg_269" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="783.35359" y="109.98206" width="13" height="25" id="svg_270" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="771.93707" cy="122.8035" id="svg_271" rx="16" ry="16" stroke="#000" class="dx window"/>
   <ellipse fill="#3f7f00" cx="771.93707" cy="122.8035" id="svg_272" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_288">
   <rect fill="#f2efef" x="716.77039" y="95.20428" width="28" height="56" id="svg_274" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="703.77039" y="110.06142" width="13" height="25" id="svg_275" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="727.93706" cy="122.78761" id="svg_276" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="727.93706" cy="122.78761" id="svg_287" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_293">
   <rect fill="#f2efef" x="437.10375" y="94.23206" width="28" height="56" id="svg_289" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="465.35359" y="108.98206" width="13" height="25" id="svg_290" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="453.93707" cy="121.8035" id="svg_291" rx="16" ry="16" stroke="#000" class="dx window"/>
   <ellipse fill="#3f7f00" cx="453.93707" cy="121.8035" id="svg_292" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_342">
   <rect fill="#f2efef" x="398.77039" y="94.20428" width="28" height="56" id="svg_294" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="385.77039" y="109.06142" width="13" height="25" id="svg_295" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="409.93706" cy="121.78761" id="svg_296" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="409.93706" cy="121.78761" id="svg_341" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_352">
   <rect fill="#f2efef" x="289.10375" y="95.23206" width="28" height="56" id="svg_343" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="317.35359" y="109.98206" width="13" height="25" id="svg_344" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="305.93707" cy="122.8035" id="svg_345" rx="16" ry="16" stroke="#000" class="dx window"/>
   <ellipse fill="#3f7f00" cx="305.93707" cy="122.8035" id="svg_351" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_367">
   <rect fill="#f2efef" x="250.77039" y="95.20428" width="28" height="56" id="svg_353" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="237.77039" y="110.06142" width="13" height="25" id="svg_354" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="261.93706" cy="122.78761" id="svg_355" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="261.93706" cy="122.78761" id="svg_366" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_372">
   <rect fill="#f2efef" x="121.10375" y="94.23206" width="28" height="56" id="svg_368" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="149.35359" y="108.98206" width="13" height="25" id="svg_369" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="137.93707" cy="121.8035" id="svg_370" rx="16" ry="16" stroke="#000" class="dx window"/>
   <ellipse fill="#3f7f00" cx="137.93707" cy="121.8035" id="svg_371" rx="12" ry="12" stroke="#000" data-id = "9cf123b1-b477-4fc2-9f33-5175434da59c" @click="bookingDesk($event)"/>
  </g>
  <g id="svg_382">
   <rect fill="#f2efef" x="605.10375" y="95.23206" width="28" height="56" id="svg_373" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="633.35359" y="109.98206" width="13" height="25" id="svg_374" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="621.93707" cy="122.8035" id="svg_375" rx="16" ry="16" stroke="#000" class="dx window"/>
   <ellipse fill="#3f7f00" cx="621.93707" cy="122.8035" id="svg_381" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_432">
   <rect fill="#f2efef" x="566.77039" y="95.20428" width="28" height="56" id="svg_383" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="553.77039" y="110.06142" width="13" height="25" id="svg_384" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="577.93706" cy="122.78761" id="svg_385" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="577.93706" cy="122.78761" id="svg_431" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_437">
   <rect fill="#f2efef" x="82.77039" y="94.20428" width="28" height="56" id="svg_433" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="69.77039" y="109.06142" width="13" height="25" id="svg_434" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="93.93706" cy="121.78761" id="svg_435" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="93.93706" cy="121.78761" id="svg_436" rx="12" ry="12" stroke="#000" data-id = "83b473a7-5502-4949-a718-67e1fec24f90" @click="bookingDesk($event)"/>
  </g>
  <g id="svg_442">
   <rect fill="#f2efef" x="1096.10375" y="797.23206" width="28" height="56" id="svg_438" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1124.35359" y="811.98206" width="13" height="25" id="svg_439" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1112.93707" cy="824.8035" id="svg_440" rx="16" ry="16" stroke="#000" class="dx window"/>
   <ellipse fill="#3f7f00" cx="1112.93707" cy="824.8035" id="svg_441" rx="12" ry="12" stroke="#000"/>
  </g>
  <g id="svg_447">
   <rect fill="#f2efef" x="1057.77039" y="797.20428" width="28" height="56" id="svg_443" filter="url(#svg_21_blur)" rx="8" stroke="#000"/>
   <rect fill="#f2efef" x="1044.77039" y="812.06142" width="13" height="25" id="svg_444" rx="2" stroke="#000"/>
   <ellipse fill="#ffffff" cx="1068.93706" cy="824.78761" id="svg_445" rx="16" ry="16" stroke="#000" class="sx window"/>
   <ellipse fill="#3f7f00" cx="1068.93706" cy="824.78761" id="svg_446" rx="12" ry="12" stroke="#000"/>
  </g>

</svg>
          </Transition>
        </div> 
      </v-col>
      <v-col
        cols="12"
        md="5"
        class="my-12 mx-auto"
        justify="center"
        style="max-width: 400px"
      >
        <Transition enter-active-class="animate__animated animate__fadeIn">
          <div v-if="deskDetails && isSvgVisible" class="desk-details">
            <v-card elevation="5" v-if="deskDetails.type!=='meeting_room'">
              <v-card-title class="headline">Desk Details</v-card-title>
              <v-card-text>
                <v-list>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title
                        ><strong>Name desk:</strong>
                        {{ deskDetails.name }}</v-list-item-title
                      >
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title
                        ><strong>Type:</strong>
                        {{ deskDetails.type }}</v-list-item-title
                      >
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title
                        ><strong>Equipment:</strong>
                        {{ deskDetails.equipment }}</v-list-item-title
                      >
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title
                        ><strong>Floor:</strong>
                        {{ deskDetails.floor }}</v-list-item-title
                      >
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title
                        ><strong>Price:</strong>
                        {{ deskDetails.pricePerH * 8 }}€</v-list-item-title
                      >
                    </v-list-item-content>
                  </v-list-item>

                  <v-list-item>
                    <p>Assign to:<br><br></p>
                    <v-select 
                      :items="employeeList" 
                      item-value="user.id" 
                      item-title="fullName" 
                      label="User" 
                      v-model="booking.userId" 
                      :disabled="isAddClicked"
                      variant="outlined"
                    >
                    </v-select>
                  </v-list-item>

                </v-list>
              </v-card-text>
              <v-card-actions>
                <v-btn color="primary" @click="assignBookingToUser" :disabled="!booking.userId"
                  >Add</v-btn
                >
              </v-card-actions>
            </v-card>
            <v-card elevation="5" v-else>
              <v-card-title class="headline">Meeting Room Details</v-card-title>
              <v-card-text>
                <v-list>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title
                        ><strong>Name room:</strong>
                        {{ deskDetails.name }}</v-list-item-title
                      >
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title
                        ><strong>Number of seats:</strong>
                        {{ deskDetails.numberOfSeats }}</v-list-item-title
                      >
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title
                        ><strong>Floor:</strong>
                        {{ deskDetails.floor }}</v-list-item-title
                      >
                    </v-list-item-content>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title
                        ><strong>Price:</strong>
                        {{ deskDetails.pricePerH * 8 }}€</v-list-item-title
                      >
                    </v-list-item-content>
                  </v-list-item>

                  <v-list-item>
                    <p>Assign to:<br><br></p>
                      <v-select
                        class="mt-4"
                        clearable
                        label="Select"
                        :items="employeeList"
                        item-value="user"
                        item-title="fullName"
                        v-model="meetingRoomList"
                        chips
                        multiple
                        variant="outlined"
                        :disabled="isAddClicked || (savedUsersMeetingRoomList.find((desk) => desk.meeting_room_name === deskDetails.name) && savedUsersMeetingRoomList.find((desk) => desk.meeting_room_name === deskDetails.name).users.length >= deskDetails.numberOfSeats)"
                    >
                  </v-select>
                </v-list-item>

                </v-list>
              </v-card-text>
              <v-card-actions>
                <v-btn color="primary" @click="assignMeetingRoomToUsers" :disabled="meetingRoomList.length == 0 || isAddClicked"
                  >Add</v-btn
                >
              </v-card-actions>
            </v-card>
          </div>
        </Transition>
      </v-col>
      <v-fade-transition hide-on-leave>
        <v-dialog
          v-model="alertVisible"
          persistent
          min-width="500"
          max-width="800"
        >
          <v-card
            class="mx-auto booking-alert"
            elevation="16"
            min-width="500"
            max-width="800"
            :title="
              alertType === 'success' ? 'Confirmed booking' : (alertType === 'warning' ? 'Warning!' : 'Error booking')
            "
            :text="alertText" style="white-space: pre-line;"
          >
            <v-divider v-if="alertType !== 'warning'"></v-divider>
  
            <div class="py-12 text-center" v-if="alertType !== 'warning'">
              <v-icon
                class="mb-6"
                size="100"
                :color="alertType === 'success' ? 'success' : 'error'"
              >
                {{
                  alertType === "success"
                    ? "mdi-check-circle-outline"
                    : "mdi-alert-outline"
                }}
              </v-icon>
  

            </div>
  
            <v-divider></v-divider>
  
            <div class="pa-4 text-end">
              <v-btn
                class="text-none"
                color="medium-emphasis"
                min-width="92"
                variant="outlined"
                rounded
                @click="goToRecap"
              >
                Close
              </v-btn>
            </div>
          </v-card>
        </v-dialog>
      </v-fade-transition>
    </v-row>

    <v-row class="d-flex justify-center align-center" >
      <div class="mx-16" v-if="isSvgVisible && bookingList.length > 0">
        <v-data-table
          class="mx-auto my-16"
          :headers="headers"
          :items="bookingList"
          style="box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3)"
        >

        <template v-slot:top>
        <v-toolbar flat class="bg-blue-grey-lighten-3">
          <v-toolbar-title>Bookings</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-spacer></v-spacer>
          <v-dialog v-model="dialog" max-width="500px">
            <template v-slot:activator>
              <v-btn
                class="mb-2"
                color="primary"
                dark
                @click="createBooking"
              >
                Confirm booking
              </v-btn>
            </template>
          </v-dialog>
        </v-toolbar>
        </template>


        <template v-slot:item.actions="{ item }">
          <v-icon
            size="small"
            @click="deleteItem(item)"
            :class="{
              disabled:
                item.status !== 'inactive'
                  ? false
                  : true,
            }"
          >
            mdi-delete
          </v-icon>
        </template>
        </v-data-table>
      </div>
    </v-row>

  </template>
  
  <script>

    /**
   * Vue component for managing desk and meeting room bookings.
   * This component allows users to view available desks, select a desk or meeting room for booking,
   * and create new bookings for individual employees or groups.
   *
   * Features:
   * - Displays a date picker to select the booking start date.
   * - Highlights available desks and marks occupied desks.
   * - Allows users to select a desk and view its details before booking.
   * - Submits booking details to create new bookings.
   * - Manages bookings for meeting rooms with multiple users.
   * - Shows alerts for booking conflicts or errors.
   * 
   * Data properties:
   * @vue-data {boolean} isSvgVisible - Flag to determine SVG visibility.
   * @vue-data {boolean} isAddClicked - Flag to determine if the 'Add' button has been clicked.
   * @vue-data {Array} savedUsersMeetingRoomList - List of users already assigned to the selected meeting room.
   * @vue-data {Array} meetingRoomList - List of users to be assigned to the selected meeting room.
   * @vue-data {Array} selectedEmployees - List of employees selected for booking.
   * @vue-data {Array} employeeWithBooking - List of employees with existing bookings.
   * @vue-data {Array} employeeList - List of employees available for booking.
   * @vue-data {Array} bookingList - List of bookings created in the current session.
   * @vue-data {boolean} dataLoaded - Flag to determine if the data has been fully loaded.
   * @vue-data {string} companyId - ID of the user's company.
   * @vue-data {string} userId - ID of the current user.
   * @vue-data {Date|null} selectedDate - The date selected for booking.
   * @vue-data {Object} booking - Contains details for the booking, including startDate, endDate, status, hasPenalty, workStationId, and userId.
   * @vue-data {Array} headers - Column headers for displaying employee bookings in a table.
   * @vue-data {Object|null} deskDetails - Details of the selected desk.
   * @vue-data {boolean} alertVisible - Flag to control the visibility of alerts.
   * @vue-data {string} alertText - Text content for the alert.
   * @vue-data {string} alertType - Type of alert (e.g., "success", "error").
   * 
   * Methods:
   * @vue-method {Function} formatDate - Formats a date to 'YYYY-MM-DD' format.
   * @vue-method {Function} findOccupiedDesks - Finds available desks for booking and updates the UI.
   * @vue-method {Function} bookingDesk - Handles the event when a user selects a desk for booking.
   * @vue-method {Function} createBooking - Creates a new booking with the provided details.
   * @vue-method {Function} goToRecap - Redirects the user to the booking list page after successful booking.
   * @vue-method {Function} assignBookingToUser - Assigns a booking to a user and updates the booking list.
   * @vue-method {Function} assignMeetingRoomToUsers - Assigns a meeting room to multiple users and updates the booking list.
   * @vue-method {Function} deleteItem - Deletes a booking from the booking list.
   *
   * Usage:
   * This component is used within a Vue application to manage desk and meeting room booking functionality.
   * It integrates with backend APIs to retrieve desk availability, create new bookings, and manage bookings for multiple users.
   * @subcategory views/bookings
   */

  export default {
    props: {
      bookingId: {
        type: String,
      },
    },
  
    data() {
      return {
        isSvgVisible: false,
        isAddClicked: false,
        savedUsersMeetingRoomList: [],
        meetingRoomList: [],
        selectedEmployees: [],
        employeeWithBooking: [],
        employeeList: [],
        bookingList: [],
        dataLoaded: false,
        companyId: "",
        userId: "",
        selectedDate: null,
        booking: {
          startDate: "",
          endDate: "",
          status: "",
          hasPenalty: false,
          workStationId: "",
          userId: "",
        },

        headers: [
          { title: "Surname", key: "user.surname" },
          { title: "Name", key: "user.name" },
          { title: "User type", key: "user.type" },
          { title: "WorkStation", key: "workstation.name" },
          { title: "Actions", key: "actions", sortable: false },
        ],
        
        deskDetails: null,
        alertVisible: false,
        alertText: "",
        alertType: "success",
      };
      
    },
  
    mounted() {
      this.userId = localStorage.getItem("userId");
    },
  
    methods: {
  
      /**
       * Formats date to 'YYYY-MM-DD' format.
       * This method takes a Date object and returns a string representation of the date
       * in the format 'YYYY-MM-DD'. This format is commonly used for sending dates to APIs
       * or displaying them in input fields.
       * @param {Date} date - The date to format.
       * @returns {string} - The formatted date string.
       */
      formatDate(date) {
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, "0");
        const day = date.getDate().toString().padStart(2, "0");
  
        return `${year}-${month}-${day}`;
      },
  
      /**
       * Finds available desks for booking.
       * This method retrieves the list of available desks for booking based on the selected start date.
       * It queries the backend API to check for desk availability and updates the UI accordingly.
       * Additionally, it highlights desks that are unavailable, such as those already booked or restricted
       * based on user permissions.
       */
      findOccupiedDesks() {
        console.log(this.selectedDate);
        this.deskDetails = null;
        this.bookingList = [];
        this.employeeWithBooking = [];
        this.employeeList = [];
        const date = this.formatDate(this.selectedDate);
        const userId = this.userId;
  
        this.$ApiService.find_user_by_id(userId).then((u) => {
          this.companyId = u.data.companyId;  
          document.querySelectorAll("[data-id]").forEach((desk) => {
            let workStationId = desk.getAttribute("data-id");
            desk.setAttribute("fill", "green");
            desk.style.pointerEvents = "auto";
  
            this.$ApiService.find_desk_by_id(workStationId).then((ws) => {
              if (ws.data.type == "meeting_room" && u.data.type !== "BUSINESS") {
                desk.setAttribute("fill", "red");
                desk.style.pointerEvents = "none";
              }
            });
          });
          
          this.$ApiService.find_occupied_desks(date).then((occupiedDesks) => {
            occupiedDesks.data.forEach((wsOccupied) => {
              const deskElement = document.querySelector(
                `[data-id="${wsOccupied.workStationId}"]`,
              );
              if (deskElement && wsOccupied.status !== "canceled") {
                deskElement.setAttribute("fill", "red");
                deskElement.style.pointerEvents = "none";
              
                  if(wsOccupied.user.company.id == this.companyId){
                      this.employeeWithBooking.push({
                          user: wsOccupied.user,
                      });
                  }
              }
            });
            this.dataLoaded = true;
            console.log(this.employeeWithBooking);
            if(this.employeeWithBooking.length > 0){
              this.alertVisible = true;
              this.alertType = "warning";
              let employeeNames = this.employeeWithBooking.map(employee => 
                `• ${employee.user.name} ${employee.user.surname}${employee.user.id === this.userId ? ' (You)' : ''}`
              ).join('\n');
              this.alertText = "It is not possible to reserve a desk for the following users because they alredy have a booking for the current day:\n\n" + employeeNames;
            }

          });
          
        });
        this.isSvgVisible = true;
      },   
      
      /**
       * Handles desk booking event.
       * This method is triggered when a user clicks on a desk to book it.
       * It retrieves the details of the selected desk from the backend and displays them to the user,
       * allowing them to review the desk information before confirming the booking.
       * @param {Event} event - The click event triggered when selecting a desk.
       */
      bookingDesk(event) {
        this.isAddClicked = false;
        const workStationId = event.target.getAttribute("data-id");
        const date = this.formatDate(this.selectedDate);
        this.employeeList = [];
  
        this.$ApiService
          .find_desk_by_id(workStationId)
          .then((res) => {
            this.deskDetails = res.data;
            this.booking.workStationId = workStationId;
          })
          .catch((deskError) => {
            console.error("Error finding desk:", deskError);
          });
        
        this.$ApiService.get_list_employee(this.companyId).then((res) => {
          res.data.forEach((employee) => { 
            if (employee.status !== "inactive" && !this.employeeWithBooking.some(e => e.user.id === employee.id)) {
              if(employee.id != this.userId){
                this.employeeList.push({
                  user: employee,
                  fullName: `${employee.surname} ${employee.name}`,
                });
              } else {
                this.employeeList.push({
                  user: employee,
                  fullName: `${employee.surname} ${employee.name} (You)`,
                });
              }
              
            }
          });
        });
      },
  
      /**
       * Creates a new booking.
       * This method is called when the user confirms the booking after selecting a desk.
       * It sends the booking details to the backend API to create a new booking record.
       * Upon successful booking creation, it displays a success message to the user.
       * If an error occurs during the booking process, an error message is displayed.
       */
      createBooking() {
        let bookingListOnly = this.bookingList.map(item => item.booking);
        let mailingList = this.bookingList.map(item => item.user.email);
        let bookingBusiness = {
          bookingDate: this.booking.startDate,
          userId: this.userId,
        };
    
        let bookingBusinessId = "";

        this.$ApiService.create_booking_business(bookingBusiness).then((res) => {
          bookingBusinessId = res.data.bookingBusinessId;
          bookingListOnly.forEach(booking => {
            booking.bookingBusinessId = bookingBusinessId;
          });

          this.$ApiService.save_booking_list(bookingListOnly).then((res) => {
            console.log(res);
            this.alertType = "success";
            this.alertText = "Booking confirmed successfully!";
            this.alertVisible = true;

            bookingListOnly.forEach(booking => {
              const deskElement = document.querySelector(`[data-id="${booking.workStationId}"]`);
              if (deskElement) {
                deskElement.setAttribute("fill", "red");
                deskElement.style.pointerEvents = "none";
              }
            });

            let messages = this.bookingList.map(item => {
              return `${item.user.name} ${item.user.surname} -> ${item.workstation.name}`;
            });
            let messageString = messages.join('\n');
            let bookingDate = this.formatDate(this.selectedDate);
            console.log(bookingDate)

            const emailData = {
              to: mailingList,
              subject: "New booking",
              text: `A reservation has been created for your team for ${bookingDate}.\n Below is the list of users with the available desk: \n` + messageString,
            };
            console.log(emailData);
            this.$ApiService.send_mail_list(emailData).then((res) => {
              console.log(res);
            }).catch((error) => {
              console.log(error);
            });

          }).catch((error) => {
            this.alertVisible = true;
            this.alertType = "error";
            this.alertText = "Something went wrong, please try again!";
            console.log(error);
          });

        }).catch((error) => {
          console.log(error);
        });
      
      },
  
      /**
       * Redirects to the booking recap page.
       * This method is called after a successful booking creation. It hides the alert and redirects the user to the booking list page.
       */
      goToRecap() {
        this.alertVisible = false;
        if (this.alertType == "success") {
          this.$router.push("/bookingList");
        }
      },

      /**
       * Assigns a booking to a user.
       * This method assigns the booking details to the selected user and updates the booking list.
       * It ensures that the desk is marked as booked and prevents further selections until the booking is modified or canceled.
       */
      assignBookingToUser(){
        this.isAddClicked = true;

        let date = new Date(this.selectedDate);
        date.setDate(date.getDate() + 1);
        let dateToSave = date.toISOString().replace('Z', '+00:00');
        const regexTime = /\d{2}:\d{2}:\d{2}\.\d{3}/;
        const newData = dateToSave.replace(regexTime, "00:00:00.000");

        this.booking.startDate = newData;
        this.booking.endDate = this.booking.startDate;
        this.booking.status = "active";

        console.log(this.booking.startDate);

        let desk = document.querySelector(`[data-id="${this.booking.workStationId}"]`);
        if (desk) {
          desk.setAttribute("fill", "gray");
          desk.style.pointerEvents = "auto";
          desk.style.cursor = "pointer";
        }

        const newBooking = Object.assign({}, this.booking);
        const index = this.bookingList.findIndex(booking => booking.booking.workStationId === newBooking.workStationId);
        let removedBooking;
        if (index !== -1) {
          removedBooking = this.bookingList.splice(index, 1);
        }
        
        if (removedBooking) {
          this.employeeWithBooking = this.employeeWithBooking.filter(employee => employee.user.id !== removedBooking[0].booking.userId);
        }

        this.$ApiService.find_user_by_id(this.booking.userId).then((u) => {
            this.employeeWithBooking.push({
                user: u.data,
            });
            this.bookingList.push({
                booking: newBooking,
                user: u.data,
                workstation: this.deskDetails,
            });
        });
        
        this.booking.userId = null;
      },

      /**
       * Assigns a meeting room to multiple users.
       * This method assigns the selected meeting room to multiple users and updates the booking list accordingly.
       * It checks if the number of users does not exceed the number of available seats in the meeting room.
       * If there are too many users selected, it displays an error message.
       */
      assignMeetingRoomToUsers(){
        let usersInMeetingRoom = this.savedUsersMeetingRoomList.length + this.meetingRoomList.length;
        console.log(this.savedUsersMeetingRoomList.length, this.meetingRoomList.length);
        if(usersInMeetingRoom > this.deskDetails.numberOfSeats){
          this.alertVisible = true;
          this.alertType = "error";
          this.alertText = "You have selected more users than the number of seats available!";
          return;
        }
        else {
          this.isAddClicked = true;

          let date = new Date(this.selectedDate);
          date.setDate(date.getDate() + 1);
          let dateToSave = date.toISOString().replace('Z', '+00:00');
          const regexTime = /\d{2}:\d{2}:\d{2}\.\d{3}/;
          const newData = dateToSave.replace(regexTime, "00:00:00.000");
          
          this.booking.startDate = newData;
          this.booking.endDate = this.booking.startDate;
          this.booking.status = "active";

          let desk = document.querySelector(`[data-id="${this.booking.workStationId}"]`);
          if (desk) {
            desk.setAttribute("fill", "gray");
            desk.style.pointerEvents = "auto";
            desk.style.cursor = "pointer";
          }

          this.meetingRoomList.forEach((user) => {
            const newBooking = Object.assign({}, this.booking);
            newBooking.userId = user.id;
            const index = this.bookingList.findIndex(booking => booking.user.id === newBooking.userId);
            let removedBooking;
            if (index !== -1) {
              removedBooking = this.bookingList.splice(index, 1);
            }
            
            if (removedBooking) {
              this.employeeWithBooking = this.employeeWithBooking.filter(employee => employee.user.id !== removedBooking[0].booking.userId);
            }

            this.$ApiService.find_user_by_id(user.id).then((u) => {
                this.employeeWithBooking.push({
                    user: u.data,
                });
                this.bookingList.push({
                    booking: newBooking,
                    user: u.data,
                    workstation: this.deskDetails,
                });
            });
          });
          //this.savedUsersMeetingRoomList = this.savedUsersMeetingRoomList.concat(this.meetingRoomList.filter(user => !this.savedUsersMeetingRoomList.some(u => u.id === user.id)));
          let room = this.savedUsersMeetingRoomList.find(room => room.meeting_room_name === this.deskDetails.name);
          if (room) {
            room.users.push(...this.meetingRoomList);
          } else {
            this.savedUsersMeetingRoomList.push({
              meeting_room_name: this.deskDetails.name,
              users: this.meetingRoomList,
            });
          }

          this.meetingRoomList = [];
        }
      },

      /**
       * Deletes a booking.
       * This method removes the selected booking from the booking list and updates the list of employees with existing bookings.
       * It also updates the UI to mark the desk as available if there are no more bookings for it.
       * @param {Object} item - The booking item to delete.
       */
      deleteItem(item){
        let deskName = item.workstation.name;
        let usersInMeetingRoom = [];
        this.employeeList = [];

        let index = this.bookingList.findIndex(booking => booking.user.id === item.user.id);
        this.bookingList.splice(index, 1);

        index = this.employeeWithBooking.findIndex(employee => employee.user.id === item.booking.userId);
        this.employeeWithBooking.splice(index, 1);

        if(item.workstation.type === "meeting_room"){
          index = this.meetingRoomList.findIndex(user => user.id === item.booking.userId);
          this.meetingRoomList.splice(index, 1);

          usersInMeetingRoom = this.savedUsersMeetingRoomList.find(room => room.meeting_room_name === deskName).users;
          index = usersInMeetingRoom.findIndex(user => user.id === item.booking.userId);
          usersInMeetingRoom.splice(index, 1);
          let room = this.savedUsersMeetingRoomList.find(room => room.meeting_room_name === deskName);
          if (room) {
            room.users = usersInMeetingRoom;
          }
        }
        console.log(usersInMeetingRoom.length)
        let desk = document.querySelector(`[data-id="${item.booking.workStationId}"]`);
        if(desk && item.workstation.type === "meeting_room" && usersInMeetingRoom.length === 0)
        {
          desk.setAttribute("fill", "green");
          desk.style.pointerEvents = "auto";
          desk.style.cursor = "pointer"; 
        } 
        else if (desk && item.workstation.type !== "meeting_room"){
          desk.setAttribute("fill", "green");
          desk.style.pointerEvents = "auto";
          desk.style.cursor = "pointer";
        }

        this.$ApiService.get_list_employee(this.companyId).then((res) => {
          res.data.forEach((employee) => { 
            if (employee.status !== "inactive" && !this.employeeWithBooking.some(e => e.user.id === employee.id)) {
              if(employee.id != this.userId){
                this.employeeList.push({
                  user: employee,
                  fullName: `${employee.surname} ${employee.name}`,
                });
              } else {
                this.employeeList.push({
                  user: employee,
                  fullName: `${employee.surname} ${employee.name} (You)`,
                });
              }
              
            }
          });
        });

        console.log(this.savedUsersMeetingRoomList);
      },

    },
  };
  </script>
  
  <style>
  .booking-alert {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 9999;
    background-color: white;
    padding: 20px;
  }
  
  .text-center {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .text-h4 {
    margin-top: 20px;
  }
  
  .v-card__text {
    max-width: 800px;
    overflow-wrap: break-word;
  }
  
  ellipse[fill="green"] {
    cursor: pointer;
  }
  
  .svg-container {
    overflow: auto;
  }
  </style>
  