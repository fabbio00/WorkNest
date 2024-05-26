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
      Book your desk
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
              v-model="booking.startDate"
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
    <v-col cols="12" md="3">
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
            width="900"
            height="600"
            xmlns="http://www.w3.org/2000/"
          >
            <defs>
              <filter id="svg_21_blur">
                <feGaussianBlur stdDeviation="0" in="SourceGraphic" />
              </filter>
            </defs>
            <g>
              <title>Layer 1</title>
              <line
                stroke="#000"
                stroke-linecap="undefined"
                stroke-linejoin="undefined"
                id="svg_5"
                y2="585.08183"
                x2="17"
                y1="53"
                x1="17"
                fill="none"
              />
              <line
                stroke="#000"
                stroke-linecap="undefined"
                stroke-linejoin="undefined"
                id="svg_7"
                y2="585"
                x2="507.18118"
                y1="585"
                x1="17"
                fill="none"
              />
              <g id="svg_29">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_21"
                  height="56"
                  width="28"
                  y="81"
                  x="80"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_22"
                  height="25"
                  width="13"
                  y="96"
                  x="67"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_30">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_24"
                  cy="109"
                  cx="91"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="d4853dcb-e483-468c-9d7a-85173028c63e"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_28"
                  cy="109"
                  cx="91"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_33">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_31"
                  height="56"
                  width="28"
                  y="148"
                  x="81"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_32"
                  height="25"
                  width="13"
                  y="163"
                  x="68"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_36">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_34"
                  cy="176"
                  cx="92"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="cb3b8cba-05a5-48f0-8c98-d4f726965841"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_35"
                  cy="176"
                  cx="92"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 141.5 109)" id="svg_51">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_49"
                  height="56"
                  width="28"
                  y="81"
                  x="134"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_50"
                  height="25"
                  width="13"
                  y="96"
                  x="121"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_54">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_52"
                  cy="109"
                  cx="138"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="e8c09506-0b0f-4539-b80f-0349842699ed"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_53"
                  cy="109"
                  cx="138"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 142.5 176)" id="svg_57">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_55"
                  height="56"
                  width="28"
                  y="148"
                  x="135"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_56"
                  height="25"
                  width="13"
                  y="163"
                  x="122"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_60">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_58"
                  cy="175"
                  cx="139"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="08502a3c-402c-428f-bfe7-e883cd6b682b"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_59"
                  cy="175"
                  cx="139"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_63">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_61"
                  height="56"
                  width="28"
                  y="78"
                  x="242"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_62"
                  height="25"
                  width="13"
                  y="93"
                  x="229"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_66">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_64"
                  cy="106"
                  cx="253"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="e2e169d3-c64e-4912-80b0-c7510fb1f7e7"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_65"
                  cy="106"
                  cx="253"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_69">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_67"
                  height="56"
                  width="28"
                  y="145"
                  x="243"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_68"
                  height="25"
                  width="13"
                  y="160"
                  x="230"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_72">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_70"
                  cy="173"
                  cx="254"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="957e31be-e283-48f5-8c96-cf6a41279017"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_71"
                  cy="173"
                  cx="254"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 303.5 106)" id="svg_75">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_73"
                  height="56"
                  width="28"
                  y="78"
                  x="296"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_74"
                  height="25"
                  width="13"
                  y="93"
                  x="283"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_78">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_76"
                  cy="106"
                  cx="300"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="a63eec37-65e7-484b-8e2e-d4c7fc79a928"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_77"
                  cy="106"
                  cx="300"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 304.5 173)" id="svg_81">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_79"
                  height="56"
                  width="28"
                  y="145"
                  x="297"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_80"
                  height="25"
                  width="13"
                  y="160"
                  x="284"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_84">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_82"
                  cy="172"
                  cx="301"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="960e2d84-1618-4109-8088-15da69d8c1e1"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_83"
                  cy="172"
                  cx="301"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_87">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_85"
                  height="56"
                  width="28"
                  y="77"
                  x="408"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_86"
                  height="25"
                  width="13"
                  y="92"
                  x="395"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_90">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_88"
                  cy="105"
                  cx="419"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="7a66bc1c-b0b3-4d12-a56a-3e49139534ca"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_89"
                  cy="105"
                  cx="419"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_93">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_91"
                  height="56"
                  width="28"
                  y="144"
                  x="409"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_92"
                  height="25"
                  width="13"
                  y="159"
                  x="396"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_96">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_94"
                  cy="172"
                  cx="420"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="4ad0e33a-82c8-409e-bcac-aa788ed3d2ff"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_95"
                  cy="172"
                  cx="420"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 469.5 105)" id="svg_99">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_97"
                  height="56"
                  width="28"
                  y="77"
                  x="462"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_98"
                  height="25"
                  width="13"
                  y="92"
                  x="449"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_102">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_100"
                  cy="105"
                  cx="466"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="4e12a22d-30fd-4029-bdce-e4754c82000b"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_101"
                  cy="105"
                  cx="466"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 470.5 172)" id="svg_105">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_103"
                  height="56"
                  width="28"
                  y="144"
                  x="463"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_104"
                  height="25"
                  width="13"
                  y="159"
                  x="450"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_108">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_106"
                  cy="171"
                  cx="467"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="c9800449-3050-4203-adc0-43c6bbb5f96f"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_107"
                  cy="171"
                  cx="467"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_111">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_109"
                  height="56"
                  width="28"
                  y="275"
                  x="84"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_110"
                  height="25"
                  width="13"
                  y="290"
                  x="71"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_114">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_112"
                  cy="303"
                  cx="95"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="36f8d6a4-299e-4cca-8ba8-8fd8c2578dc3"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_113"
                  cy="303"
                  cx="95"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_117">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_115"
                  height="56"
                  width="28"
                  y="342"
                  x="85"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_116"
                  height="25"
                  width="13"
                  y="357"
                  x="72"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_120">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_118"
                  cy="370"
                  cx="96"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="92bbcbeb-2845-4ad0-8051-132f83968185"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_119"
                  cy="370"
                  cx="96"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 145.5 303)" id="svg_123">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_121"
                  height="56"
                  width="28"
                  y="275"
                  x="138"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_122"
                  height="25"
                  width="13"
                  y="290"
                  x="125"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_126">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_124"
                  cy="303"
                  cx="142"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="08ea3555-db10-41b6-999f-4da0a2f1a293"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_125"
                  cy="303"
                  cx="142"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 146.5 370)" id="svg_129">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_127"
                  height="56"
                  width="28"
                  y="342"
                  x="139"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_128"
                  height="25"
                  width="13"
                  y="357"
                  x="126"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_132">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_130"
                  cy="369"
                  cx="143"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="0d212398-dd13-46af-a711-cf980d00c1d1"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_131"
                  cy="369"
                  cx="143"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_135">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_133"
                  height="56"
                  width="28"
                  y="409"
                  x="84"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_134"
                  height="25"
                  width="13"
                  y="424"
                  x="71"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_138">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_136"
                  cy="437"
                  cx="95"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="992f87da-1e30-4c0a-8f90-d8feed78dcf5"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_137"
                  cy="437"
                  cx="95"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 145.5 437)" id="svg_141">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_139"
                  height="56"
                  width="28"
                  y="409"
                  x="138"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_140"
                  height="25"
                  width="13"
                  y="424"
                  x="125"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_144">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_142"
                  cy="436"
                  cx="142"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="590d6d7d-a533-4f1a-ae44-d2ba3d426e11"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_143"
                  cy="436"
                  cx="142"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_147">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_145"
                  height="56"
                  width="28"
                  y="476"
                  x="84"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_146"
                  height="25"
                  width="13"
                  y="491"
                  x="71"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_150">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_148"
                  cy="504"
                  cx="95"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="1e73ae60-8b9c-4947-b8b1-eb50eef1b03f"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_149"
                  cy="504"
                  cx="95"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 145.5 504)" id="svg_153">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_151"
                  height="56"
                  width="28"
                  y="476"
                  x="138"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_152"
                  height="25"
                  width="13"
                  y="491"
                  x="125"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_156">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_154"
                  cy="503"
                  cx="142"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="2a091352-be82-425f-a85b-c9c3fecd9974"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_155"
                  cy="503"
                  cx="142"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_159">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_157"
                  height="56"
                  width="28"
                  y="275"
                  x="242"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_158"
                  height="25"
                  width="13"
                  y="290"
                  x="229"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_162">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_160"
                  cy="303"
                  cx="253"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="3ba6cc39-07cc-436f-a495-516bcbca5843"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_161"
                  cy="303"
                  cx="253"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_165">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_163"
                  height="56"
                  width="28"
                  y="342"
                  x="243"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_164"
                  height="25"
                  width="13"
                  y="357"
                  x="230"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_168">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_166"
                  cy="370"
                  cx="254"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="048d4521-fa2b-4aff-9d8f-c136659363ca"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_167"
                  cy="370"
                  cx="254"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 303.5 303)" id="svg_171">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_169"
                  height="56"
                  width="28"
                  y="275"
                  x="296"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_170"
                  height="25"
                  width="13"
                  y="290"
                  x="283"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_174">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_172"
                  cy="303"
                  cx="300"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="54c6318e-5c7b-4c92-93f7-7ea5a40d480a"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_173"
                  cy="303"
                  cx="300"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 304.5 370)" id="svg_177">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_175"
                  height="56"
                  width="28"
                  y="342"
                  x="297"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_176"
                  height="25"
                  width="13"
                  y="357"
                  x="284"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_180">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_178"
                  cy="369"
                  cx="301"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="e702347a-0527-4c2f-b17a-7dcdf2fef95d"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_179"
                  cy="369"
                  cx="301"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_183">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_181"
                  height="56"
                  width="28"
                  y="409"
                  x="242"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_182"
                  height="25"
                  width="13"
                  y="424"
                  x="229"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_186">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_184"
                  cy="437"
                  cx="253"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="a40c1d82-ed0c-4835-b6a0-70d7a202afcd"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_185"
                  cy="437"
                  cx="253"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 303.5 437)" id="svg_189">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_187"
                  height="56"
                  width="28"
                  y="409"
                  x="296"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_188"
                  height="25"
                  width="13"
                  y="424"
                  x="283"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_192">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_190"
                  cy="436"
                  cx="300"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="1bcc6d12-5fe1-43a7-9224-c2d0de4c7514"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_191"
                  cy="436"
                  cx="300"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_195">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_193"
                  height="56"
                  width="28"
                  y="476"
                  x="242"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_194"
                  height="25"
                  width="13"
                  y="491"
                  x="229"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_198">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_196"
                  cy="504"
                  cx="253"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="ee35b609-093d-4e50-9e46-859391b7d01d"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_197"
                  cy="504"
                  cx="253"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 303.5 504)" id="svg_201">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_199"
                  height="56"
                  width="28"
                  y="476"
                  x="296"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_200"
                  height="25"
                  width="13"
                  y="491"
                  x="283"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_204">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_202"
                  cy="503"
                  cx="300"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="ad723744-0fc6-431c-a620-5ff289ed68d7"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_203"
                  cy="503"
                  cx="300"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_207">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_205"
                  height="56"
                  width="28"
                  y="279"
                  x="405"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_206"
                  height="25"
                  width="13"
                  y="294"
                  x="392"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_210">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_208"
                  cy="307"
                  cx="416"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="1bfc7b14-8324-4e9f-8040-a81347ca06bc"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_209"
                  cy="307"
                  cx="416"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_213">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_211"
                  height="56"
                  width="28"
                  y="346"
                  x="406"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_212"
                  height="25"
                  width="13"
                  y="361"
                  x="393"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_216">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_214"
                  cy="374"
                  cx="417"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="28ae76c9-9ae9-418d-a21c-a89b3fd5e9ca"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_215"
                  cy="374"
                  cx="417"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 466.5 307)" id="svg_219">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_217"
                  height="56"
                  width="28"
                  y="279"
                  x="459"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_218"
                  height="25"
                  width="13"
                  y="294"
                  x="446"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_222">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_220"
                  cy="307"
                  cx="463"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="dca9335c-ae25-4b8c-98f5-8090cf0b0a62"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_221"
                  cy="307"
                  cx="463"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 467.5 374)" id="svg_225">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_223"
                  height="56"
                  width="28"
                  y="346"
                  x="460"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_224"
                  height="25"
                  width="13"
                  y="361"
                  x="447"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_228">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_226"
                  cy="373"
                  cx="464"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="d1f6b04b-cfb8-417e-afb0-22806d657ed0"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_227"
                  cy="373"
                  cx="464"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_231">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_229"
                  height="56"
                  width="28"
                  y="413"
                  x="405"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_230"
                  height="25"
                  width="13"
                  y="428"
                  x="392"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_234">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_232"
                  cy="441"
                  cx="416"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="b3fd12ec-4415-4012-9efc-bc7a1fdf89c0"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_233"
                  cy="441"
                  cx="416"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 466.5 441)" id="svg_237">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_235"
                  height="56"
                  width="28"
                  y="413"
                  x="459"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_236"
                  height="25"
                  width="13"
                  y="428"
                  x="446"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_240">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_238"
                  cy="440"
                  cx="463"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="e7a1608f-6f5f-4f55-95e9-1377f26802b1"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_239"
                  cy="440"
                  cx="463"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_243">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_241"
                  height="56"
                  width="28"
                  y="480"
                  x="405"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_242"
                  height="25"
                  width="13"
                  y="495"
                  x="392"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_246">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_244"
                  cy="508"
                  cx="416"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="47c3a3e2-ce12-4331-8865-02e2c0ab27b3"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_245"
                  cy="508"
                  cx="416"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 466.5 508)" id="svg_249">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_247"
                  height="56"
                  width="28"
                  y="480"
                  x="459"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_248"
                  height="25"
                  width="13"
                  y="495"
                  x="446"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_252">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_250"
                  cy="507"
                  cx="463"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="43582b98-cd28-4d6e-9518-3d159b28bb68"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_251"
                  cy="507"
                  cx="463"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <line
                stroke="#000"
                id="svg_253"
                y2="53"
                x2="297.53076"
                y1="53"
                x1="17"
                fill="none"
              />
              <line
                stroke="#000"
                id="svg_256"
                y2="53"
                x2="509.01073"
                y1="53"
                x1="404.13953"
                fill="none"
              />
              <line
                stroke="#000"
                id="svg_258"
                y2="586.01178"
                x2="508.00174"
                y1="52.66719"
                x1="508.00174"
                fill="none"
              />
              <line
                stroke="#000"
                id="svg_259"
                y2="53.33386"
                x2="664.0137"
                y1="53.33386"
                x1="507.33507"
                fill="none"
              />
              <g id="svg_263">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_261"
                  height="56"
                  width="28"
                  y="73.66666"
                  x="566.6669"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_262"
                  height="25"
                  width="13"
                  y="88.66666"
                  x="553.6669"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_266">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_264"
                  cy="101.66666"
                  cx="577.6669"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="b2d1a53e-7bd5-4d96-980c-fead4b4ae634"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_265"
                  cy="101.66666"
                  cx="577.6669"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_269">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_267"
                  height="56"
                  width="28"
                  y="140.66666"
                  x="567.6669"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_268"
                  height="25"
                  width="13"
                  y="155.66666"
                  x="554.6669"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_272">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_270"
                  cy="168.66666"
                  cx="578.6669"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="e3ba5436-bacc-4893-a8ce-0eb6a92ab3dc"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_271"
                  cy="168.66666"
                  cx="578.6669"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 628.167 101.667)" id="svg_275">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_273"
                  height="56"
                  width="28"
                  y="73.66666"
                  x="620.6669"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_274"
                  height="25"
                  width="13"
                  y="88.66666"
                  x="607.6669"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_278">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_276"
                  cy="101.66666"
                  cx="624.6669"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="28d62173-7f14-43e5-a7bf-35a322695cf2"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_277"
                  cy="101.66666"
                  cx="624.6669"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 629.167 168.667)" id="svg_281">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_279"
                  height="56"
                  width="28"
                  y="140.66666"
                  x="621.6669"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_280"
                  height="25"
                  width="13"
                  y="155.66666"
                  x="608.6669"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_284">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_282"
                  cy="167.66666"
                  cx="625.6669"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="f486f10c-afae-420e-bf24-ac0e2a3651f9"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_283"
                  cy="167.66666"
                  cx="625.6669"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_287">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_285"
                  height="56"
                  width="28"
                  y="74.33333"
                  x="749.33385"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_286"
                  height="25"
                  width="13"
                  y="89.33333"
                  x="736.33385"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_290">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_288"
                  cy="102.33333"
                  cx="760.33385"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="d369fc70-73e5-4563-8fc2-2cc8116a021f"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_289"
                  cy="102.33333"
                  cx="760.33385"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_293">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_291"
                  height="56"
                  width="28"
                  y="141.33333"
                  x="750.33385"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_292"
                  height="25"
                  width="13"
                  y="156.33333"
                  x="737.33385"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_296">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_294"
                  cy="169.33333"
                  cx="761.33385"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="c5f19007-e249-4c3e-82d3-d10db52ca88c"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_295"
                  cy="169.33333"
                  cx="761.33385"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 810.834 102.334)" id="svg_299">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_297"
                  height="56"
                  width="28"
                  y="74.33333"
                  x="803.33385"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_298"
                  height="25"
                  width="13"
                  y="89.33333"
                  x="790.33385"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_302">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_300"
                  cy="102.33333"
                  cx="807.33385"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="b8bf6efa-6c0a-4677-b90a-fcf605937083"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_301"
                  cy="102.33333"
                  cx="807.33385"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 811.834 169.334)" id="svg_305">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_303"
                  height="56"
                  width="28"
                  y="141.33333"
                  x="804.33385"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_304"
                  height="25"
                  width="13"
                  y="156.33333"
                  x="791.33385"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_308">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_306"
                  cy="168.33333"
                  cx="808.33385"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="e4a7209b-be94-4c58-8796-19aeccb15c3e"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_307"
                  cy="168.33333"
                  cx="808.33385"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_311">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_309"
                  height="56"
                  width="28"
                  y="206.66676"
                  x="567.6669"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_310"
                  height="25"
                  width="13"
                  y="221.66676"
                  x="554.6669"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_314">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_312"
                  cy="234.66676"
                  cx="578.6669"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="0169f9a5-e57a-43a4-a88d-4ce40605f225"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_313"
                  cy="234.66676"
                  cx="578.6669"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 629.167 234.667)" id="svg_317">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_315"
                  height="56"
                  width="28"
                  y="206.66676"
                  x="621.6669"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_316"
                  height="25"
                  width="13"
                  y="221.66676"
                  x="608.6669"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_320">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_318"
                  cy="233.66676"
                  cx="625.6669"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="e041bec9-b6d7-4866-8585-a5e9d0ebe6f5"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_319"
                  cy="233.66676"
                  cx="625.6669"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g id="svg_323">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_321"
                  height="56"
                  width="28"
                  y="206.00009"
                  x="751.00051"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_322"
                  height="25"
                  width="13"
                  y="221.00009"
                  x="738.00051"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_326">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_324"
                  cy="234.00009"
                  cx="762.00051"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="0571b046-4134-4388-818d-e47d9fae0f7e"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_325"
                  cy="234.00009"
                  cx="762.00051"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <g transform="rotate(-180 812.501 234.001)" id="svg_329">
                <rect
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_327"
                  height="56"
                  width="28"
                  y="206.00009"
                  x="805.00051"
                  fill="#f2efef"
                />
                <rect
                  stroke="#000"
                  rx="2"
                  id="svg_328"
                  height="25"
                  width="13"
                  y="221.00009"
                  x="792.00051"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_332">
                <ellipse
                  stroke="#000"
                  ry="16"
                  rx="16"
                  id="svg_330"
                  cy="233.00009"
                  cx="809.00051"
                  fill="#ffffff"
                />
                <ellipse
                  data-id="8f1937bf-d34a-4164-9cfb-0b3a496633d7"
                  stroke="#000"
                  ry="12"
                  rx="12"
                  id="svg_331"
                  cy="233.00009"
                  cx="809.00051"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
              <line
                stroke="#000"
                id="svg_333"
                y2="284.66754"
                x2="860.67141"
                y1="284.66754"
                x1="508.00174"
                fill="none"
              />
              <line
                id="svg_334"
                y2="286.04821"
                x2="861.3356"
                y1="56.00053"
                x1="861.3356"
                stroke="#000"
                fill="none"
              />
              <line
                id="svg_335"
                y2="55.33386"
                x2="740.65209"
                y1="55.33386"
                x1="860.66894"
                stroke="#000"
                fill="none"
              />
              <line
                stroke="#000"
                id="svg_336"
                y2="584.00132"
                x2="861.57794"
                y1="584.00132"
                x1="508.00174"
                fill="none"
              />
              <line
                stroke="#000"
                id="svg_338"
                y2="584.668"
                x2="861.3356"
                y1="376.00102"
                x1="861.3356"
                fill="none"
              />
              <g id="svg_400">
                <rect
                  transform="rotate(-90 678.668 429.002)"
                  stroke="#000"
                  rx="8"
                  filter="url(#svg_21_blur)"
                  id="svg_347"
                  height="236.00027"
                  width="84.66675"
                  y="311.00211"
                  x="636.3346"
                  fill="#f2efef"
                />
                <rect
                  transform="rotate(-90 640.002 293.002)"
                  stroke="#000"
                  rx="2"
                  id="svg_348"
                  height="25"
                  width="13"
                  y="231.33527"
                  x="546.0011"
                  fill="#f2efef"
                />
                <rect
                  transform="rotate(-90 695.335 292.336)"
                  stroke="#000"
                  rx="2"
                  id="svg_387"
                  height="25"
                  width="13"
                  y="230.6686"
                  x="601.33451"
                  fill="#f2efef"
                />
                <rect
                  transform="rotate(-90 750.001 292.335)"
                  stroke="#000"
                  rx="2"
                  id="svg_388"
                  height="25"
                  width="13"
                  y="230.6686"
                  x="656.00126"
                  fill="#f2efef"
                />
                <rect
                  transform="rotate(-90 802.668 292.335)"
                  stroke="#000"
                  rx="2"
                  id="svg_389"
                  height="25"
                  width="13"
                  y="230.6686"
                  x="708.668"
                  fill="#f2efef"
                />
                <rect
                  transform="rotate(-90 639.335 389.669)"
                  stroke="#000"
                  rx="2"
                  id="svg_390"
                  height="25"
                  width="13"
                  y="328.00208"
                  x="545.33443"
                  fill="#f2efef"
                />
                <rect
                  transform="rotate(-90 694.001 389.669)"
                  stroke="#000"
                  rx="2"
                  id="svg_391"
                  height="25"
                  width="13"
                  y="328.00208"
                  x="600.00118"
                  fill="#f2efef"
                />
                <rect
                  transform="rotate(-90 750.002 389.002)"
                  stroke="#000"
                  rx="2"
                  id="svg_392"
                  height="25"
                  width="13"
                  y="327.33541"
                  x="656.00126"
                  fill="#f2efef"
                />
                <rect
                  transform="rotate(-90 802.668 389.002)"
                  stroke="#000"
                  rx="2"
                  id="svg_393"
                  height="25"
                  width="13"
                  y="327.33541"
                  x="708.668"
                  fill="#f2efef"
                />
              </g>
              <g id="svg_404">
                <rect
                  rx="8"
                  id="svg_401"
                  height="61.33343"
                  width="61.33343"
                  y="396.66771"
                  x="648.00195"
                  stroke="#000"
                  fill="#ffffff"
                />
                <rect
                  data-id="6cdece00-bd46-4853-a683-d337c69082f0"
                  stroke="#000"
                  rx="8"
                  id="svg_403"
                  height="53.33341"
                  width="53.33341"
                  y="400.66771"
                  x="652.00196"
                  fill="green"
                  @click="bookingDesk($event)"
                />
              </g>
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
          <v-card elevation="5">
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
              </v-list>
            </v-card-text>
            <v-card-actions>
              <v-btn v-if="!bookingId" color="primary" @click="createBooking"
                >Book</v-btn
              >
              <v-btn v-else color="primary" @click="editBooking">Modify</v-btn>
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
            alertType === 'success' ? 'Confirmed booking' : 'Error booking'
          "
        >
          <v-divider></v-divider>

          <div class="py-12 text-center">
            <v-icon
              class="mb-6"
              size="128"
              :color="alertType === 'success' ? 'success' : 'error'"
            >
              {{
                alertType === "success"
                  ? "mdi-check-circle-outline"
                  : "mdi-alert-outline"
              }}
            </v-icon>

            <div class="text-h4 font-weight-bold" style="word-wrap: break-word">
              {{ alertText }}
            </div>
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
</template>

<script>
/**
 * Vue component for managing desk booking.
 * This component allows users to view available desks, select a desk for booking,
 * and create a new booking.
 *
 * Features:
 * - Displays a date picker to select the booking start date.
 * - Highlights available desks and marks occupied desks.
 * - Allows users to select a desk and view its details before booking.
 * - Submits booking details to create a new booking.
 *
 * Data properties:
 * @vue-data {boolean} isSvgVisible - Flag to determine SVG visibility.
 * @vue-data {Object} booking - Contains details for the booking, including startDate, endDate, status, hasPenalty, workStationId, and userId.
 * @vue-data {Object|null} deskDetails - Details of the selected desk.
 * @vue-data {boolean} alertVisible - Flag to control the visibility of alerts.
 * @vue-data {string} alertText - Text content for the alert.
 * @vue-data {string} alertType - Type of alert.
 * @vue-data {Object} modifyBooking - Contains details for modifying an existing booking, including startDate, endDate, and workStationId.
 *
 * Methods:
 * @vue-method {Function} formatDate - Formats date to YYYY-MM-DD format.
 * @vue-method {Function} formatDate2 - Formats date to ISO 8601 format.
 * @vue-method {Function} findOccupiedDesks - Finds available desks for booking and updates UI accordingly.
 * @vue-method {Function} bookingDesk - Handles desk booking event, retrieves desk details.
 * @vue-method {Function} createBooking - Creates a new booking with the provided details.
 * @vue-method {Function} editBooking - Modifies an existing booking with the provided details.
 * @vue-method {Function} goToRecap - Redirects the user to the booking list page after successful booking or modification.
 *
 * Usage:
 * This component is used within a Vue application to manage desk booking functionality.
 * It integrates with backend APIs to retrieve desk availability and create new bookings.
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
      booking: {
        startDate: null,
        endDate: null,
        status: "",
        hasPenalty: false,
        workStationId: "",
        userId: "",
      },
      modifyBooking: {
        startDate: null,
        endDate: null,
        workStationId: "",
      },
      deskDetails: null,
      alertVisible: false,
      alertText: "",
      alertType: "success",
    };
  },

  mounted() {
    this.booking.userId = localStorage.getItem("userId");
  },

  methods: {
    /**
     * Formats date to YYYY-MM-DD format.
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
     * Formats date to ISO 8601 format.
     * This method takes a string representation of a date and formats it to conform to the
     * ISO 8601 standard. The resulting string includes the date and time in 'YYYY-MM-DDTHH:MM:SSZ' format,
     * suitable for APIs that expect dates in ISO format.
     * @param {string} dateString - The date string to format.
     * @returns {string} - The formatted ISO 8601 date string.
     */
    formatDate2(dateString) {
      const date = new Date(dateString);

      const year = date.getFullYear();
      const month = (date.getMonth() + 1).toString().padStart(2, "0");
      const day = date.getDate().toString().padStart(2, "0");
      const hours = date.getHours().toString().padStart(2, "0");
      const minutes = date.getMinutes().toString().padStart(2, "0");
      const seconds = date.getSeconds().toString().padStart(2, "0");

      const isoString = `${year}-${month.padStart(2, "0")}-${parseInt(day).toString().padStart(2, "0")}T${hours}:${minutes}:${seconds}+00:00`;

      return isoString;
    },

    /**
     * Finds available desks for booking.
     * This method retrieves the list of available desks for booking based on the selected start date.
     * It queries the backend API to check for desk availability and updates the UI accordingly.
     * Additionally, it highlights desks that are unavailable, such as those already booked or restricted
     * based on user permissions.
     */
    findOccupiedDesks() {
      const date = this.formatDate(this.booking.startDate);
      const userId = this.booking.userId;

      this.$ApiService.find_user_by_id(userId).then((u) => {
        document.querySelectorAll("[data-id]").forEach((desk) => {
          let workStationId = desk.getAttribute("data-id");
          desk.setAttribute("fill", "green");
          desk.style.pointerEvents = "auto";

          this.$ApiService.find_desk_by_id(workStationId).then((ws) => {
            if (ws.data.type == "meeting room" && u.data.type !== "business") {
              desk.setAttribute("fill", "red");
              desk.style.pointerEvents = "none";
            }
          });
        });
      });

      this.$ApiService.find_occupied_desks(date).then((occupiedDesks) => {
        occupiedDesks.data.forEach((ws) => {
          const deskElement = document.querySelector(
            `[data-id="${ws.workStationId}"]`,
          );
          if (deskElement && ws.status !== "canceled") {
            deskElement.setAttribute("fill", "red");
            deskElement.style.pointerEvents = "none";
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
      const workStationId = event.target.getAttribute("data-id");
      const date = this.formatDate(this.booking.startDate);

      this.$ApiService
        .find_desk_by_id(workStationId)
        .then((res) => {
          this.deskDetails = res.data;
          this.booking.workStationId = workStationId;
        })
        .catch((deskError) => {
          console.error("Error finding desk:", deskError);
        });
    },

    /**
     * Creates a new booking.
     * This method is called when the user confirms the booking after selecting a desk.
     * It sends the booking details to the backend API to create a new booking record.
     * Upon successful booking creation, it displays a success message to the user.
     * If an error occurs during the booking process, it displays an error message instead.
     */
    createBooking() {
      this.booking.startDate = this.formatDate2(this.booking.startDate);
      this.booking.endDate = this.booking.startDate;
      this.booking.status = "active";
      const wsId = this.booking.workStationId;

      this.$ApiService
        .create_booking(this.booking)
        .then((res) => {
          this.alertVisible = true;
          this.alertType = "success";
          this.alertText = "Registration was successful!";

          const deskElement = document.querySelector(`[data-id="${wsId}"]`);
          if (deskElement) {
            deskElement.setAttribute("fill", "red");
            deskElement.style.pointerEvents = "none";
          }

          const userId = this.booking.userId;
          const bookingDate = new Date(this.booking.startDate);
          const formattedDate = bookingDate.toLocaleDateString("en-US", {
            year: "numeric",
            month: "long",
            day: "numeric",
          });

          this.$ApiService.find_desk_by_id(wsId).then((res) => {
            this.$ApiService
              .find_user_by_id(userId)
              .then((u) => {
                const emailData = {
                  to: u.data.email,
                  subject: "Desk Booking Confirmation",
                  text: `Your booking for the ${res.data.name} has been successfully confirmed for ${formattedDate}.`,
                };

                this.$ApiService
                  .send_mail(emailData)
                  .then((emailRes) => {})
                  .catch((emailError) => {
                    console.error("Error sending email:", emailError);
                  });
              })
              .catch((userError) => {
                console.error("Error finding user:", userError);
              });
          });
        })
        .catch((error) => {
          this.alertVisible = true;
          this.alertType = "error";
          this.alertText = "Something went wrong, please try again!";
        });
    },

    /**
     * Modifies an existing booking with the provided details.
     * This method is called when the user confirms the modification of a booking.
     * It sends the modified booking details to the backend API to update the booking record.
     * Upon successful modification, it displays a success message to the user.
     * If an error occurs during the modification process, it displays an error message instead.
     */
    editBooking() {
      this.modifyBooking.startDate = this.formatDate2(this.booking.startDate);
      this.modifyBooking.endDate = this.modifyBooking.startDate;
      this.modifyBooking.workStationId = this.booking.workStationId;
      const wsId = this.modifyBooking.workStationId;

      this.$ApiService
        .modify_booking(this.bookingId, this.modifyBooking)
        .then((res) => {
          this.alertVisible = true;
          this.alertType = "success";
          this.alertText = "Modify was successful!";

          const deskElement = document.querySelector(`[data-id="${wsId}"]`);
          if (deskElement) {
            deskElement.setAttribute("fill", "red");
            deskElement.style.pointerEvents = "none";
          }

          const userId = this.booking.userId;
          const bookingDate = new Date(this.booking.startDate);
          const formattedDate = bookingDate.toLocaleDateString("en-US", {
            year: "numeric",
            month: "long",
            day: "numeric",
          });
          this.$ApiService
            .find_user_by_id(userId)
            .then((u) => {
              const emailData = {
                to: u.data.email,
                subject: "Edit Booking Confirmed",
                text: `Your booking for the ${res.data.workStation.name} has been successfully updated for ${formattedDate}.`,
              };

              this.$ApiService
                .send_mail(emailData)
                .then((emailRes) => {
                  console.log(emailRes.data);
                })
                .catch((emailError) => {
                  console.error("Error sending email:", emailError);
                });
            })
            .catch((userError) => {
              console.error("Error finding user:", userError);
            });
        })
        .catch((error) => {
          this.alertVisible = true;
          this.alertType = "error";
          this.alertText = "Something went wrong, please try again!";
        });
    },

    goToRecap() {
      this.alertVisible = false;
      if (this.alertType == "success") {
        this.$router.push("/bookingList");
      }
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
