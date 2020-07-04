<template>
  <div id="app" v-title data-title="GreenTrip">
    <transition name="fade-transform" mode="out-in">
      <router-view/>
    </transition>

  </div>
</template>
<script>

export default {

  components: {

  }
}
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 100%;
  padding: 20px 100px 144px;
  background:rgb(245, 248, 255) url('assets/background.svg') repeat 100%;

  min-height: 800px
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}
</style>
