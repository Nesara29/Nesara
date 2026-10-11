<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EventMS – Plan the event. We run the show.</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Syne:wght@600;800&family=DM+Sans:wght@400;500;700&display=swap" rel="stylesheet">
<style>
:root{--bg:#0f0c29;--bg2:#1a1440;--bg3:#241a5a;--ink:#f3f0e8;--mute:#b3aed0;--cyan:#00d4ff;--vio:#7f5af0;--grn:#2cb67d;--red:#ff4d6d;--gold:#ffc861;--line:rgba(255,255,255,.1)}
*{box-sizing:border-box;margin:0;padding:0}
html{scroll-behavior:smooth}
body{background:var(--bg);color:var(--ink);font:400 17px/1.65 "DM Sans",system-ui,sans-serif;overflow-x:hidden}
h1,h2,h3{font-family:Syne,"DM Sans",sans-serif;line-height:1.05;font-weight:800}
a{color:inherit;text-decoration:none}
.wrap{max-width:1160px;margin:0 auto;padding:0 22px}
section{padding:100px 0}
.btn{display:inline-block;padding:15px 28px;border-radius:999px;font-weight:700;font-size:16px;border:0;cursor:pointer;transition:transform .2s,box-shadow .2s;font-family:inherit}
.btn:hover{transform:translateY(-2px)}
.btn.p{background:linear-gradient(90deg,var(--vio),var(--cyan));color:#0b0828;box-shadow:0 10px 30px rgba(0,212,255,.25)}
.btn.g{border:1.5px solid var(--line);color:var(--ink);background:rgba(255,255,255,.04)}
a:focus-visible,button:focus-visible,input:focus-visible,select:focus-visible{outline:2px solid var(--cyan);outline-offset:3px}

/* NAV */
nav{position:fixed;inset:0 0 auto 0;z-index:20;transition:background .3s}
nav.s{background:rgba(15,12,41,.9);backdrop-filter:blur(10px);border-bottom:1px solid var(--line)}
nav .wrap{display:flex;align-items:center;justify-content:space-between;height:72px}
.logo{font:800 24px Syne;letter-spacing:.5px}.logo b{color:var(--cyan)}
.links{display:flex;gap:30px;align-items:center;font-size:15px;color:var(--mute)}
.links a:hover{color:var(--ink)}
.burger{display:none;background:none;border:0;color:var(--ink);font-size:28px}

/* HERO */
.hero{min-height:100vh;display:flex;align-items:center;position:relative;padding:120px 0 80px;overflow:hidden}
.beam{position:absolute;top:-20%;width:38vw;height:130%;filter:blur(40px);opacity:.5;transform-origin:top center;pointer-events:none}
.b1{left:8%;background:linear-gradient(180deg,rgba(127,90,240,.9),transparent 70%);animation:sw 9s ease-in-out infinite alternate}
.b2{right:6%;background:linear-gradient(180deg,rgba(0,212,255,.7),transparent 70%);animation:sw 11s ease-in-out infinite alternate-reverse}
@keyframes sw{from{transform:rotate(-14deg)}to{transform:rotate(14deg)}}
.hero .wrap{position:relative;display:grid;grid-template-columns:1.15fr .85fr;gap:50px;align-items:center}
.tag{display:inline-flex;gap:8px;align-items:center;border:1px solid var(--line);border-radius:999px;padding:7px 16px;font-size:14px;color:var(--mute);background:rgba(255,255,255,.04);margin-bottom:26px}
.dot{width:8px;height:8px;border-radius:50%;background:var(--grn);box-shadow:0 0 10px var(--grn)}
h1{font-size:clamp(46px,7.4vw,98px);letter-spacing:-2px}
h1 span{display:block;opacity:0;transform:translateY(40px);animation:up .8s cubic-bezier(.2,.8,.2,1) forwards}
h1 span:nth-child(2){animation-delay:.18s;background:linear-gradient(90deg,var(--vio),var(--cyan) 60%,var(--gold));-webkit-background-clip:text;background-clip:text;color:transparent}
h1 span:nth-child(3){animation-delay:.36s}
@keyframes up{to{opacity:1;transform:none}}
.lead{margin:28px 0 36px;color:var(--mute);font-size:19px;max-width:48ch}
.cta{display:flex;gap:14px;flex-wrap:wrap}
.proof{display:flex;gap:34px;margin-top:46px;flex-wrap:wrap}
.proof b{font:800 34px Syne;display:block}.proof small{color:var(--mute);font-size:14px}

/* TICKET */
.ticket{position:relative;background:linear-gradient(160deg,var(--bg3),var(--bg2));border:1px solid rgba(127,90,240,.5);border-radius:26px;padding:32px;transform:rotate(3deg);box-shadow:0 40px 80px rgba(0,0,0,.5),0 0 60px rgba(127,90,240,.25);animation:fl 6s ease-in-out infinite}
@keyframes fl{50%{transform:rotate(1deg) translateY(-14px)}}
.ticket:before,.ticket:after{content:"";position:absolute;top:62%;width:30px;height:30px;border-radius:50%;background:var(--bg)}
.ticket:before{left:-16px}.ticket:after{right:-16px}
.ticket small{color:var(--cyan);font-weight:700;letter-spacing:1px}
.ticket h3{font-size:30px;margin:8px 0 6px}
.ticket p{color:var(--mute);font-size:15px}
.perf{border-top:2px dashed var(--line);margin:30px 0 20px}
.row{display:flex;justify-content:space-between;font-size:15px;padding:5px 0}.row span{color:var(--mute)}
.bar{height:6px;border-radius:9px;background:var(--line);margin-top:14px;overflow:hidden}.bar i{display:block;height:100%;width:72%;background:linear-gradient(90deg,var(--grn),var(--cyan))}

/* MARQUEE */
.mq{border-block:1px solid var(--line);background:var(--bg2);overflow:hidden;padding:20px 0}
.mq div{display:flex;gap:56px;width:max-content;animation:mq 30s linear infinite;font:800 28px Syne;color:rgba(255,255,255,.35)}
.mq span:nth-child(even){color:var(--vio)}
@keyframes mq{to{transform:translateX(-50%)}}

/* SECTION HEAD */
.sh{max-width:640px;margin-bottom:56px}
.sh h2{font-size:clamp(34px,5vw,56px);letter-spacing:-1px;margin-bottom:16px}
.sh p{color:var(--mute)}

/* CATEGORIES */
.cats{display:grid;grid-template-columns:repeat(4,1fr);gap:18px}
.cat{height:300px;border-radius:22px;padding:24px;display:flex;flex-direction:column;justify-content:flex-end;position:relative;overflow:hidden;transition:transform .3s}
.cat:hover{transform:translateY(-8px)}
.cat em{position:absolute;top:18px;right:20px;font-size:46px;font-style:normal}
.cat h3{font-size:26px}.cat p{font-size:14px;opacity:.85;margin-top:6px}
.c1{background:linear-gradient(160deg,#7f5af0,#2a1a7a)}.c2{background:linear-gradient(160deg,#ff4d6d,#6b1534)}
.c3{background:linear-gradient(160deg,#00a8cc,#0b3a66)}.c4{background:linear-gradient(160deg,#2cb67d,#0e4a3a)}

/* STEPS */
.steps{display:grid;grid-template-columns:repeat(4,1fr);gap:22px;counter-reset:s}
.st{border-top:2px solid var(--vio);padding-top:22px;counter-increment:s}
.st:before{content:counter(s);display:grid;place-items:center;width:44px;height:44px;border-radius:50%;background:var(--vio);font:800 18px Syne;margin-bottom:18px}
.st h3{font-size:22px;margin-bottom:8px}.st p{color:var(--mute);font-size:15px}

/* PACKAGES */
.pk{display:grid;grid-template-columns:repeat(3,1fr);gap:22px;align-items:stretch}
.pc{background:var(--bg2);border:1px solid var(--line);border-radius:24px;padding:36px 30px;display:flex;flex-direction:column;position:relative}
.pc.hot{border-color:var(--vio);background:linear-gradient(180deg,var(--bg3),var(--bg2));transform:scale(1.04);box-shadow:0 30px 70px rgba(127,90,240,.3)}
.pc .pop{position:absolute;top:-14px;left:30px;background:var(--gold);color:#2a1a00;font-weight:700;font-size:13px;padding:5px 14px;border-radius:99px}
.pc h3{font-size:26px}.pc .pr{font:800 44px Syne;margin:14px 0 4px}.pc .pr small{font:400 15px "DM Sans";color:var(--mute)}
.pc ul{list-style:none;margin:22px 0 30px;flex:1}
.pc li{padding:7px 0 7px 28px;position:relative;font-size:15px;color:#ddd9ef}
.pc li:before{content:"✓";position:absolute;left:0;color:var(--grn);font-weight:700}
.pc .btn{text-align:center}

/* GALLERY */
.gal{display:grid;grid-template-columns:repeat(4,1fr);grid-auto-rows:170px;gap:14px}
.gi{border-radius:18px;display:flex;align-items:flex-end;padding:16px;font-weight:700;overflow:hidden;transition:transform .3s,filter .3s;filter:saturate(.85)}
.gi:hover{transform:scale(1.03);filter:none}
.gi:nth-child(1){grid-column:span 2;grid-row:span 2;background:radial-gradient(circle at 30% 20%,#ffc861,#ff4d6d 40%,#3a0f4a)}
.gi:nth-child(2){background:linear-gradient(135deg,#00d4ff,#2a1a7a)}
.gi:nth-child(3){background:linear-gradient(135deg,#2cb67d,#0b3a66)}
.gi:nth-child(4){grid-row:span 2;background:linear-gradient(200deg,#7f5af0,#ff4d6d)}
.gi:nth-child(5){background:linear-gradient(135deg,#ffc861,#7f5af0)}
.gi:nth-child(6){grid-column:span 2;background:linear-gradient(120deg,#1a1440,#00a8cc)}

/* BOOK */
.bk{background:linear-gradient(135deg,var(--bg3),var(--bg2));border:1px solid var(--line);border-radius:30px;padding:56px;display:grid;grid-template-columns:1fr 1fr;gap:56px}
.bk h2{font-size:clamp(32px,4.4vw,50px);margin-bottom:16px}.bk>div>p{color:var(--mute)}
form{display:grid;gap:16px}
label{font-size:14px;color:var(--mute);display:grid;gap:7px}
input,select{background:rgba(255,255,255,.06);border:1px solid var(--line);border-radius:12px;padding:14px 16px;color:var(--ink);font:inherit}
select option{color:#000}
.sum{background:rgba(0,0,0,.25);border-radius:14px;padding:16px 18px;font-size:15px;color:var(--mute)}.sum b{color:var(--cyan)}

/* ROLES */
.roles{display:grid;grid-template-columns:repeat(3,1fr);gap:20px}
.rl{border:1px solid var(--line);border-radius:22px;padding:30px;background:var(--bg2)}
.rl i{font-style:normal;font-size:34px}.rl h3{font-size:22px;margin:12px 0 8px}.rl p{color:var(--mute);font-size:15px;margin-bottom:18px}
.rl a{color:var(--cyan);font-weight:700}

/* QUOTES */
.qs{display:grid;grid-template-columns:repeat(3,1fr);gap:20px}
.q{background:var(--bg2);border-radius:22px;padding:30px}.q p{font-size:17px;margin-bottom:20px}
.q b{display:block}.q small{color:var(--mute)}

/* FOOTER */
.fin{text-align:center;padding:120px 0;background:radial-gradient(ellipse at 50% 100%,rgba(127,90,240,.45),transparent 65%)}
.fin h2{font-size:clamp(38px,6vw,76px);letter-spacing:-1.5px;margin-bottom:30px}
footer{border-top:1px solid var(--line);padding:34px 0;color:var(--mute);font-size:14px}
footer .wrap{display:flex;justify-content:space-between;flex-wrap:wrap;gap:16px}

@media(max-width:960px){
 .links{position:fixed;inset:72px 0 auto 0;background:var(--bg);flex-direction:column;padding:26px;gap:20px;border-bottom:1px solid var(--line);transform:translateY(-130%);transition:transform .3s}
 .links.o{transform:none}.burger{display:block}
 .hero .wrap,.bk{grid-template-columns:1fr}.bk{padding:30px}
 .ticket{max-width:380px;transform:none;animation:none}
 .cats,.steps,.gal{grid-template-columns:repeat(2,1fr)}
 .pk,.roles,.qs{grid-template-columns:1fr}.pc.hot{transform:none}
 section{padding:70px 0}
}
@media(prefers-reduced-motion:reduce){*{animation:none!important;transition:none!important}h1 span{opacity:1;transform:none}}
</style>
</head>
<body>

<nav id="nav"><div class="wrap">
  <a class="logo" href="#top">Event<b>MS</b></a>
  <div class="links" id="links">
    <a href="#events">Events</a><a href="#how">How it works</a><a href="#packages">Packages</a><a href="#gallery">Gallery</a><a href="#portals">Login</a>
    <a class="btn p" href="#book" style="padding:11px 22px">Book now</a>
  </div>
  <button class="burger" id="bg" aria-label="Menu">☰</button>
</div></nav>

<header class="hero" id="top">
  <div class="beam b1"></div><div class="beam b2"></div>
  <div class="wrap">
    <div>
      <div class="tag"><i class="dot"></i> Now booking weddings, launches &amp; celebrations</div>
      <h1><span>Plan it once.</span><span>We run the show.</span><span>You enjoy it.</span></h1>
      <p class="lead">Pick an event type, choose a package, and track every booking and payment from one dashboard.</p>
      <div class="cta"><a class="btn p" href="#book">Book your event</a><a class="btn g" href="#packages">See packages</a></div>
      <div class="proof"><div><b>1,200+</b><small>Events delivered</small></div><div><b>4.9/5</b><small>Client rating</small></div><div><b>3 portals</b><small>Client · Subadmin · Admin</small></div></div>
    </div>
    <aside class="ticket" aria-label="Sample booking">
      <small>BOOKING #EV-2048</small>
      <h3>Aarav &amp; Meera's Wedding</h3>
      <p>Grand Ballroom · 14 Dec · 300 guests</p>
      <div class="perf"></div>
      <div class="row"><span>Package</span><b>Premium</b></div>
      <div class="row"><span>Status</span><b style="color:var(--grn)">Approved</b></div>
      <div class="row"><span>Payment</span><b>72% paid</b></div>
      <div class="bar"><i></i></div>
    </aside>
  </div>
</header>

<div class="mq" aria-hidden="true"><div>
  <span>Weddings</span><span>✦</span><span>Birthdays</span><span>✦</span><span>Corporate</span><span>✦</span><span>Concerts</span><span>✦</span><span>Engagements</span><span>✦</span><span>Product Launches</span><span>✦</span>
  <span>Weddings</span><span>✦</span><span>Birthdays</span><span>✦</span><span>Corporate</span><span>✦</span><span>Concerts</span><span>✦</span><span>Engagements</span><span>✦</span><span>Product Launches</span><span>✦</span>
</div></div>

<section id="events"><div class="wrap">
  <div class="sh"><h2>Whatever you're celebrating</h2><p>Browse event categories, then open any package to see exactly what's included.</p></div>
  <div class="cats">
    <a class="cat c1" href="#packages"><em>💍</em><h3>Weddings</h3><p>Venue, décor, catering, music</p></a>
    <a class="cat c2" href="#packages"><em>🎂</em><h3>Birthdays</h3><p>Themes for every age</p></a>
    <a class="cat c3" href="#packages"><em>🏢</em><h3>Corporate</h3><p>Conferences, launches, offsites</p></a>
    <a class="cat c4" href="#packages"><em>🎤</em><h3>Concerts</h3><p>Stage, sound, ticketing</p></a>
  </div>
</div></section>

<section id="how" style="background:var(--bg2)"><div class="wrap">
  <div class="sh"><h2>From idea to applause in four steps</h2><p>Every booking moves through clear stages, and you can see where yours stands at any time.</p></div>
  <div class="steps">
    <div class="st"><h3>Choose</h3><p>Pick a category and a package that fits your guest list and budget.</p></div>
    <div class="st"><h3>Book</h3><p>Sign up, add your date and venue, and submit. Status: <b>Booked</b>.</p></div>
    <div class="st"><h3>Approve</h3><p>Our team reviews it and assigns a subadmin. Status: <b>Approved</b>.</p></div>
    <div class="st"><h3>Celebrate</h3><p>We run the event, you pay in stages. Status: <b>Live → Completed</b>.</p></div>
  </div>
</div></section>

<section id="packages"><div class="wrap">
  <div class="sh"><h2>Simple packages, no surprises</h2><p>Every package lists its features up front. Upgrade any time before approval.</p></div>
  <div class="pk">
    <div class="pc"><h3>Essential</h3><div class="pr">₹49k<small> / event</small></div><p style="color:var(--mute);font-size:15px">Small gatherings up to 100 guests</p>
      <ul><li>Venue booking support</li><li>Basic décor</li><li>Event coordinator on the day</li><li>Online booking tracking</li></ul><a class="btn g" href="#book">Choose Essential</a></div>
    <div class="pc hot"><span class="pop">Most booked</span><h3>Premium</h3><div class="pr">₹1.2L<small> / event</small></div><p style="color:var(--mute);font-size:15px">Up to 300 guests, full service</p>
      <ul><li>Everything in Essential</li><li>Themed décor &amp; lighting</li><li>Catering management</li><li>Photo &amp; video team</li><li>Staged online payments</li></ul><a class="btn p" href="#book">Choose Premium</a></div>
    <div class="pc"><h3>Signature</h3><div class="pr">₹3L+<small> / event</small></div><p style="color:var(--mute);font-size:15px">Large-scale, fully custom</p>
      <ul><li>Everything in Premium</li><li>Dedicated planning team</li><li>Stage, sound &amp; entertainment</li><li>Guest management</li><li>Priority support</li></ul><a class="btn g" href="#book">Choose Signature</a></div>
  </div>
</div></section>

<section id="gallery" style="background:var(--bg2)"><div class="wrap">
  <div class="sh"><h2>Moments we've made</h2><p>A look at recent events from our gallery.</p></div>
  <div class="gal">
    <div class="gi">Royal wedding, Jaipur</div><div class="gi">Tech summit</div><div class="gi">Garden birthday</div>
    <div class="gi">Winter concert</div><div class="gi">Engagement night</div><div class="gi">Product launch, Bengaluru</div>
  </div>
</div></section>

<section id="book"><div class="wrap"><div class="bk">
  <div>
    <h2>Check your booking in 30 seconds</h2>
    <p>Choose your event details to see a quick summary. Create a free client account to confirm and pay.</p>
    <div class="sum" style="margin-top:28px" id="sum" aria-live="polite">Pick an event type and package to see your summary.</div>
  </div>
  <form id="f">
    <label>Event type<select id="t"><option value="">Select…</option><option>Wedding</option><option>Birthday</option><option>Corporate event</option><option>Concert</option></select></label>
    <label>Package<select id="p"><option value="">Select…</option><option value="Essential|₹49,000">Essential</option><option value="Premium|₹1,20,000">Premium</option><option value="Signature|₹3,00,000+">Signature</option></select></label>
    <label>Event date<input type="date" id="d"></label>
    <label>Guests<input type="number" id="g" min="10" placeholder="e.g. 150"></label>
    <button class="btn p" type="submit">Continue to sign up</button>
  </form>
</div></div></section>

<section id="portals" style="background:var(--bg2)"><div class="wrap">
  <div class="sh"><h2>One platform, the right view for everyone</h2></div>
  <div class="roles">
    <div class="rl"><i>👤</i><h3>Clients</h3><p>Create bookings, track status, make payments and manage your profile.</p><a href="/home/client-login">Client login →</a></div>
    <div class="rl"><i>🧭</i><h3>Subadmins</h3><p>See assigned bookings and update progress on the ground.</p><a href="/subadmin/login">Subadmin login →</a></div>
    <div class="rl"><i>🛠️</i><h3>Admins</h3><p>Run categories, packages, gallery, users and the task calendar.</p><a href="/home/admin-login">Admin login →</a></div>
  </div>
</div></section>

<section><div class="wrap">
  <div class="sh"><h2>Loved by the people we work for</h2></div>
  <div class="qs">
    <div class="q"><p>I tracked my wedding booking from approval to payment without calling anyone once.</p><b>Meera S.</b><small>Wedding client</small></div>
    <div class="q"><p>The assigned-booking view gives my team exactly what we need on the day.</p><b>Rohit K.</b><small>Event coordinator</small></div>
    <div class="q"><p>Packages and gallery updates take minutes now. The dashboard saves us hours every week.</p><b>Anita P.</b><small>Operations lead</small></div>
  </div>
</div></section>

<div class="fin"><div class="wrap">
  <h2>Your event starts here.</h2>
  <a class="btn p" href="#book">Book your event</a>
</div></div>

<footer><div class="wrap"><span>© 2026 EventMS · Built with Java, Spring Boot &amp; MySQL</span><span>Educational project</span></div></footer>

<script>
var nav=document.getElementById("nav"),L=document.getElementById("links");
addEventListener("scroll",function(){nav.classList.toggle("s",scrollY>20)});
document.getElementById("bg").onclick=function(){L.classList.toggle("o")};
L.addEventListener("click",function(e){if(e.target.tagName=="A")L.classList.remove("o")});
function upd(){
  var t=document.getElementById("t").value,p=document.getElementById("p").value.split("|"),
      d=document.getElementById("d").value,g=document.getElementById("g").value,s=document.getElementById("sum");
  if(!t||!p[0]){s.textContent="Pick an event type and package to see your summary.";return}
  s.innerHTML="<b>"+t+"</b> · "+p[0]+" package<br>Starting at <b>"+p[1]+"</b>"+(g?" · "+g+" guests":"")+(d?" · "+new Date(d).toDateString():"");
}
["t","p","d","g"].forEach(function(i){document.getElementById(i).addEventListener("input",upd)});
document.getElementById("f").onsubmit=function(e){e.preventDefault();
  if(!document.getElementById("t").value||!document.getElementById("p").value){upd();document.getElementById("t").focus();return}
  document.getElementById("sum").innerHTML="<b>Great choice.</b> Create your client account to confirm this booking.";};
</script>
</body>
</html>
